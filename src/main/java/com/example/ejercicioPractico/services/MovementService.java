package com.example.ejercicioPractico.services;

import com.example.ejercicioPractico.domain.Client;
import com.example.ejercicioPractico.domain.Movement;
import com.example.ejercicioPractico.domain.mappers.MovementMapper;
import com.example.ejercicioPractico.domain.vo.MovementVo;
import com.example.ejercicioPractico.domain.vo.ReportVo;
import com.example.ejercicioPractico.exceptions.TransactionNotFoundException;
import com.example.ejercicioPractico.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.ejercicioPractico.domain.Account;
import com.example.ejercicioPractico.domain.enums.*;
import com.example.ejercicioPractico.repository.AccountRepository;
import com.example.ejercicioPractico.repository.MovementRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class MovementService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MovementMapper movementMapper;

    public String saveMovement(MovementVo movementVo, String accountId) {
        Optional<Account> account = this.accountRepository.findById(accountId);
        if (account.isPresent()) {
            if (account.get().getStatus()) {
                Movement movement = movementMapper.toMovement(movementVo);
                List<Movement> movements = this.movementRepository.findByAccount_IdOrderByDateDesc(account.get().getId());
                BigDecimal currentBalance, balance;
                if (movements != null && !movements.isEmpty()) {
                    currentBalance =  movements.get(0).getBalance();
                } else  {
                    currentBalance = account.get().getInitialBalance();
                }
                if (movement.getMovementType().equals(MovementType.CREDITO)) {
                    balance = currentBalance.add(movement.getAmount());
                } else if (movement.getMovementType().equals(MovementType.DEBITO)) {
                    if (currentBalance.compareTo(movement.getAmount()) > 0 || currentBalance.compareTo(movement.getAmount()) == 0) {
                        balance = currentBalance.subtract(movement.getAmount());
                    } else  {
                        throw new TransactionNotFoundException("Saldo Insuficiente.");
                    }
                } else  {
                    throw new TransactionNotFoundException("Tipo de movimiento no existe.");
                }
                movement.setBalance(balance);
                movement.setAccount(account.get());
                Movement savedMovement = this.movementRepository.save(movement);
                return savedMovement.getBalance().toString();
            } else  {
                throw new TransactionNotFoundException("Cuenta en estado false.");
            }
        } else  {
            throw new TransactionNotFoundException("Cliente seleccionado no existe.");
        }        
    }

    public List<ReportVo> getMovements(String clientId, Long dateFrom, Long dateTo) {
        Client client = this.clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            LocalDateTime initialTime = LocalDateTime.of(new Date(dateFrom).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MIN);
            LocalDateTime finalTime = LocalDateTime.of(new Date(dateTo).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MAX).withNano(0);
            List<Movement> movements = new ArrayList<>();
            List<ReportVo> reportVos = new ArrayList<>();
            List<Account> accounts = this.accountRepository.findByClient_Id(clientId);
            accounts.forEach(account -> {
                List<Movement> movementsByAccount = this.movementRepository.findByAccount_IdAndDateBetweenOrderByDateDesc(account.getId(), Timestamp.valueOf(initialTime), Timestamp.valueOf(finalTime));
                if (movementsByAccount != null && !movementsByAccount.isEmpty()) {
                    movements.addAll(movementsByAccount);
                }
            } );
            if (!movements.isEmpty()) {
                movements.forEach(movement -> {
                    ReportVo reportVo =  new ReportVo();
                    reportVo.setMovementDate(movement.getDate());
                    reportVo.setClientName(client.getName());
                    reportVo.setAccountNumber(movement.getAccount().getAccountNumber());
                    reportVo.setMovementType(movement.getMovementType());
                    reportVo.setInitialBalance(movement.getAccount().getInitialBalance());
                    reportVo.setAccountStatus(movement.getAccount().getStatus());
                    reportVo.setMovementAmount(movement.getAmount());
                    reportVo.setCurrentBalance(movement.getBalance());
                    reportVos.add(reportVo);
                });
                return reportVos;
            } else {
                throw new TransactionNotFoundException("No existen movimientos.");
            }
        } else {
            throw new TransactionNotFoundException("No existe cliente con ese id.");
        }
    }
}

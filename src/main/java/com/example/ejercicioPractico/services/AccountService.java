package com.example.ejercicioPractico.services;

import com.example.ejercicioPractico.domain.Account;
import com.example.ejercicioPractico.domain.Client;
import com.example.ejercicioPractico.domain.mappers.AccountMapper;
import com.example.ejercicioPractico.domain.vo.AccountVo;
import com.example.ejercicioPractico.exceptions.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.ejercicioPractico.repository.AccountRepository;
import com.example.ejercicioPractico.repository.ClientRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountMapper accountMapper;

    public String saveAccount(AccountVo accountVo, String clientId) {
        Optional<Client> client = this.clientRepository.findById(clientId);
        if (client.isPresent()) {
            Account newAccount = accountMapper.toAccount(accountVo);
            newAccount.setClient(client.get());
            return accountRepository.save(newAccount).getId();
        } else  {
            throw new TransactionNotFoundException("Cliente seleccionado no existe.");
        }
    } 

    public String updateAccount(AccountVo accountVo, String clientId, String accountId) {
        Optional<Client> client = this.clientRepository.findById(clientId);
        if (client.isPresent()) {
            if (accountRepository.existsById(accountId)) {
                Account updatedAccount = accountMapper.toAccount(accountVo);
                updatedAccount.setId(accountId);
                updatedAccount.setClient(client.get());
                return accountRepository.save(updatedAccount).getId();
            } else  {
                throw new TransactionNotFoundException("Cuenta no existe.");
            }
        } else  {
            throw new TransactionNotFoundException("Cliente seleccionado no existe.");
        }
    }
    
    public List<AccountVo> getAllAccountsByClientId(String clientId) {
        return accountMapper.toAccountVoList(accountRepository.findByClient_Id(clientId));
    }

    public AccountVo getAccountById(String accountId) {
        Optional<Account> account = this.accountRepository.findById(accountId);
        if(account.isPresent()) {
            return accountMapper.toAccountVo(account.get());
        } else  {
            throw new TransactionNotFoundException("Cuenta no existe.");
        }
    }
    
    public boolean deleteAccount(String accountId) {
        Account account = this.accountRepository.findById(accountId).orElse(null);
        if(account != null) {
            account.setStatus(false);
            this.accountRepository.save(account);
            return true;
        } else  {
            throw new TransactionNotFoundException("Cuenta no existe.");
        }
    } 
}

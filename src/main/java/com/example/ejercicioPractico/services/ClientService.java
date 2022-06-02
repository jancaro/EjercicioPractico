package com.example.ejercicioPractico.services;

import com.example.ejercicioPractico.domain.Client;
import com.example.ejercicioPractico.domain.mappers.ClientMapper;
import com.example.ejercicioPractico.domain.vo.ClientVo;
import com.example.ejercicioPractico.exceptions.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.ejercicioPractico.repository.ClientRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    public String saveClient(ClientVo clientVo) {
        Client savedClient = this.clientRepository.findByIdentification(clientVo.getIdentification());
        if (savedClient == null) {
            Client newClient = clientMapper.toClient(clientVo);
            return clientRepository.save(newClient).getId();
        } else {
            throw new TransactionNotFoundException("Cliente con identificación duplicada.");
        }
    }

    public String updateClient(ClientVo clientVo, String id) {
        if (this.clientRepository.existsById(id)) {
            Client updatedClient = clientMapper.toClient(clientVo);
            updatedClient.setId(id);
            return clientRepository.save(updatedClient).getId();
        } else {
            throw new TransactionNotFoundException("Cliente ingresado no existe");
        }
    }

    public List<ClientVo> getAllClients() {
        return clientMapper.toClientVoList(clientRepository.findAll());
    }

    public ClientVo getClientById(String id) {
        Optional<Client> savedClient = this.clientRepository.findById(id);
        if (savedClient.isPresent()) {
            return clientMapper.toClientVo(savedClient.get());
        } else {
            throw new TransactionNotFoundException("No existe cliente con ese id.");
        }
    }

    public boolean deleteClient(String id) {
        Optional<Client> savedClient = this.clientRepository.findById(id);
        if (savedClient.isPresent()) {
            savedClient.get().setStatus(false);
            this.clientRepository.save(savedClient.get());
            return true;
        } else {
            throw new TransactionNotFoundException("Cliente no existe.");
        }

    }
}

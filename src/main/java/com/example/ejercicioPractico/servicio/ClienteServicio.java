package com.example.ejercicioPractico.servicio;

import com.example.ejercicioPractico.domain.mappers.ClienteMapper;
import com.example.ejercicioPractico.domain.vo.ClienteVo;
import com.example.ejercicioPractico.excepciones.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.ejercicioPractico.domain.Cliente;
import com.example.ejercicioPractico.repositorio.ClienteRepositorio;

@Service
@Transactional(rollbackOn = Exception.class)
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    ClienteMapper clienteMapper;

    public String saveCliente(ClienteVo clienteVo) {
        Cliente clienteGuardado = this.clienteRepositorio.findByIdentificacion(clienteVo.getIdentificacion());
        if (clienteGuardado == null) {
            Cliente nuevo = clienteMapper.toCliente(clienteVo);
            return clienteRepositorio.save(nuevo).getId();
        } else {
            throw new TransactionNotFoundException("Cliente con identificación duplicada.");
        }
    }

    public String updateCliente(ClienteVo clienteVo, String id) {
        if (this.clienteRepositorio.existsById(id)) {
            Cliente actualizado = clienteMapper.toCliente(clienteVo);
            actualizado.setId(id);
            return clienteRepositorio.save(actualizado).getId();
        } else {
            throw new TransactionNotFoundException("Cliente ingresado no existe");
        }
    }

    public List<ClienteVo> getAllClientes() {
        return clienteMapper.toClienteVoList(clienteRepositorio.findAll());
    }

    public ClienteVo getClienteById(String id) {
        Optional<Cliente> clienteGuardado = this.clienteRepositorio.findById(id);
        if (clienteGuardado.isPresent()) {
            return clienteMapper.toClienteVo(clienteGuardado.get());
        } else {
            throw new TransactionNotFoundException("No existe cliente con ese id.");
        }
    }

    public boolean deleteCliente(String id) {
        Optional<Cliente> clienteGuardado = this.clienteRepositorio.findById(id);
        if (clienteGuardado.isPresent()) {
            clienteGuardado.get().setEstado(false);
            this.clienteRepositorio.save(clienteGuardado.get());
            return true;
        } else {
            throw new TransactionNotFoundException("Cliente no existe.");
        }

    }
}

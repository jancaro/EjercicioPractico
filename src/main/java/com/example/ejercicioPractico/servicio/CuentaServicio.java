package com.example.ejercicioPractico.servicio;

import com.example.ejercicioPractico.domain.mappers.CuentaMapper;
import com.example.ejercicioPractico.domain.vo.CuentaVo;
import com.example.ejercicioPractico.excepciones.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.ejercicioPractico.domain.Cuenta;
import com.example.ejercicioPractico.domain.Cliente;
import com.example.ejercicioPractico.repositorio.CuentaRepositorio;
import com.example.ejercicioPractico.repositorio.ClienteRepositorio;

@Service
@Transactional(rollbackOn = Exception.class)
public class CuentaServicio {
    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuentaMapper cuentaMapper;

    public String saveCuenta(CuentaVo cuentaVo, String clienteId) {
        Optional<Cliente> cliente = this.clienteRepositorio.findById(clienteId);
        if (cliente.isPresent()) {
            Cuenta cuentaNueva = cuentaMapper.toCuenta(cuentaVo);
            cuentaNueva.setCliente(cliente.get());
            return cuentaRepositorio.save(cuentaNueva).getId();
        } else  {
            throw new TransactionNotFoundException("Cliente seleccionado no existe.");
        }
    } 

    public String updateCuenta(CuentaVo cuentaVo, String clienteId, String cuentaId) {
        Optional<Cliente> cliente = this.clienteRepositorio.findById(clienteId);
        if (cliente.isPresent()) {
            if (cuentaRepositorio.existsById(cuentaId)) {
                Cuenta actualizada = cuentaMapper.toCuenta(cuentaVo);
                actualizada.setId(cuentaId);
                actualizada.setCliente(cliente.get());
                return cuentaRepositorio.save(actualizada).getId();
            } else  {
                throw new TransactionNotFoundException("Cuenta no existe.");
            }
        } else  {
            throw new TransactionNotFoundException("Cliente seleccionado no existe.");
        }
    }
    
    public List<CuentaVo> getAllCuentasByClienteId(String clienteId) {
        return cuentaMapper.toCuentaVoList(cuentaRepositorio.findByCliente_Id(clienteId));
    }

    public CuentaVo getCuentaById(String cuentaId) {
        Optional<Cuenta> cuenta = this.cuentaRepositorio.findById(cuentaId);
        if(cuenta.isPresent()) {
            return cuentaMapper.toCuentaVo(cuenta.get());
        } else  {
            throw new TransactionNotFoundException("Cuenta no existe.");
        }
    }
    
    public boolean deleteCuenta(String cuentaId) {
        Cuenta cuenta = this.cuentaRepositorio.findById(cuentaId).orElse(null);
        if(cuenta != null) {
            cuenta.setEstado(false);
            this.cuentaRepositorio.save(cuenta);
            return true;
        } else  {
            throw new TransactionNotFoundException("Cuenta no existe.");
        }
    } 
}

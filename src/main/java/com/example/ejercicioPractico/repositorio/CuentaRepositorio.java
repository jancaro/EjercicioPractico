package com.example.ejercicioPractico.repositorio;

import com.example.ejercicioPractico.domain.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CuentaRepositorio extends JpaRepository<Cuenta, String> {
   List<Cuenta> findByCliente_Id(String clienteId);
   List<Cuenta> findByCliente_IdAndEstado(String clienteId, boolean estado); 
}

package com.example.ejercicioPractico.repositorio;

import java.io.Serializable;
import java.util.List;

import com.example.ejercicioPractico.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    Cliente findByIdentificacion(String identificacion);
    List<Cliente> findByEstado(boolean estado); 
}

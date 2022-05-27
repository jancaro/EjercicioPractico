package com.example.ejercicioPractico.repositorio;

import com.example.ejercicioPractico.domain.Movimiento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface MovimientoRepositorio extends JpaRepository<Movimiento, String> {
    List<Movimiento> findByCuenta_IdAndFechaBetweenOrderByFechaDesc(String cuentaId, Date minDate, Date maxDate);
    List<Movimiento> findByCuenta_IdOrderByFechaDesc(String cuentaId);
}

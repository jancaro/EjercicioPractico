package com.example.ejercicioPractico.servicio;

import com.example.ejercicioPractico.domain.Cliente;
import com.example.ejercicioPractico.domain.mappers.MovimientoMapper;
import com.example.ejercicioPractico.domain.vo.MovimientoVo;
import com.example.ejercicioPractico.domain.vo.ReporteVo;
import com.example.ejercicioPractico.excepciones.TransactionNotFoundException;
import com.example.ejercicioPractico.repositorio.ClienteRepositorio;
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

import com.example.ejercicioPractico.domain.Cuenta;
import com.example.ejercicioPractico.domain.Movimiento;
import com.example.ejercicioPractico.domain.enums.*;
import com.example.ejercicioPractico.repositorio.CuentaRepositorio;
import com.example.ejercicioPractico.repositorio.MovimientoRepositorio;

@Service
@Transactional(rollbackOn = Exception.class)
public class MovimientoServicio {
    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    @Autowired
    private MovimientoRepositorio movimientoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private MovimientoMapper movimientoMapper;

    public String saveMovimiento(MovimientoVo movimientoVo, String cuentaId) {
        Optional<Cuenta> cuenta = this.cuentaRepositorio.findById(cuentaId);
        if (cuenta.isPresent()) {
            if (cuenta.get().getEstado()) {
                Movimiento movimiento = movimientoMapper.toMovimiento(movimientoVo);
                List<Movimiento> movimientos = this.movimientoRepositorio.findByCuenta_IdOrderByFechaDesc(cuenta.get().getId());
                BigDecimal saldoActual, saldo;
                if (movimientos != null && !movimientos.isEmpty()) {
                    saldoActual =  movimientos.get(0).getSaldo();
                } else  {
                    saldoActual = cuenta.get().getSaldoInicial();
                }
                if (movimiento.getTipoMovimiento().equals(TipoMovimiento.CREDITO)) {
                    saldo = saldoActual.add(movimiento.getValor());
                } else if (movimiento.getTipoMovimiento().equals(TipoMovimiento.DEBITO)) {
                    if (saldoActual.compareTo(movimiento.getValor()) == 1 || saldoActual.compareTo(movimiento.getValor()) == 0) {
                        saldo = saldoActual.subtract(movimiento.getValor());
                    } else  {
                        throw new TransactionNotFoundException("Saldo Insuficiente.");
                    }
                } else  {
                    throw new TransactionNotFoundException("Tipo de movimiento no existe.");
                }
                movimiento.setSaldo(saldo);
                movimiento.setCuenta(cuenta.get());
                Movimiento movimientoGuardado = this.movimientoRepositorio.save(movimiento);
                return movimientoGuardado.getSaldo().toString();
            } else  {
                throw new TransactionNotFoundException("Cuenta en estado false.");
            }
        } else  {
            throw new TransactionNotFoundException("Cliente seleccionado no existe.");
        }        
    }

    public List<ReporteVo> getMovimientos(String clienteId, Long fechaDesde, Long fechaHasta) {
        Cliente cliente = this.clienteRepositorio.findById(clienteId).orElse(null);
        if (cliente != null) {
            LocalDateTime inicio = LocalDateTime.of(new Date(fechaDesde).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MIN);
            LocalDateTime finalDia = LocalDateTime.of(new Date(fechaHasta).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MAX).withNano(0);
            List<Movimiento> movimientos = new ArrayList<>();
            List<ReporteVo> reporte = new ArrayList<>();
            List<Cuenta> cuentas = this.cuentaRepositorio.findByCliente_Id(clienteId);
            cuentas.forEach(cuenta -> {
                List<Movimiento> movimientosPorCuenta = this.movimientoRepositorio.findByCuenta_IdAndFechaBetweenOrderByFechaDesc(cuenta.getId(), Timestamp.valueOf(inicio), Timestamp.valueOf(finalDia));
                if (movimientosPorCuenta != null && !movimientosPorCuenta.isEmpty()) {
                    movimientos.addAll(movimientosPorCuenta);
                }
            } );
            if (!movimientos.isEmpty()) {
                movimientos.forEach(movimiento -> {
                    ReporteVo reporteVo =  new ReporteVo();
                    reporteVo.setFechaMovimiento(movimiento.getFecha());
                    reporteVo.setNombreCliente(cliente.getNombre());
                    reporteVo.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
                    reporteVo.setTipoMovimiento(movimiento.getTipoMovimiento());
                    reporteVo.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
                    reporteVo.setEstadoCuenta(movimiento.getCuenta().getEstado());
                    reporteVo.setMontoMovimiento(movimiento.getValor());
                    reporteVo.setSaldoActual(movimiento.getSaldo());
                    reporte.add(reporteVo);
                });
                return reporte;
            } else {
                throw new TransactionNotFoundException("No existen movimientos.");
            }
        } else {
            throw new TransactionNotFoundException("No existe cliente con ese id.");
        }
    }
}

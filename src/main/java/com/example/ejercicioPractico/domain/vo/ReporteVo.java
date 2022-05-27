package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.TipoMovimiento;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReporteVo {
    private Date fechaMovimiento;
    private String nombreCliente;
    private String numeroCuenta;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal saldoInicial;
    private Boolean estadoCuenta;
    private BigDecimal montoMovimiento;
    private BigDecimal saldoActual;

}

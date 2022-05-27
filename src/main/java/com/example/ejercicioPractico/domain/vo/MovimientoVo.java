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
public class MovimientoVo {
    private Date fecha;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
}

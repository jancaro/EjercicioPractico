package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.TipoCuenta;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CuentaVo {
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
}

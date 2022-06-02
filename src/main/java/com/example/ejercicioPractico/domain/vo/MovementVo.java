package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.MovementType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovementVo {
    private Date date;
    private MovementType movementType;
    private BigDecimal amount;
    private BigDecimal balance;
}

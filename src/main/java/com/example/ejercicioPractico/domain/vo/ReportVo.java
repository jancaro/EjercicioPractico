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
public class ReportVo {
    private Date movementDate;
    private String clientName;
    private String accountNumber;
    private MovementType movementType;
    private BigDecimal initialBalance;
    private Boolean accountStatus;
    private BigDecimal movementAmount;
    private BigDecimal currentBalance;

}

package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.MovementType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportVo {
    Date movementDate;
    String clientName;
    String accountNumber;
    MovementType movementType;
    BigDecimal initialBalance;
    Boolean accountStatus;
    BigDecimal movementAmount;
    BigDecimal currentBalance;

}

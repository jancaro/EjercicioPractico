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
public class MovementVo {
    Date date;
    MovementType movementType;
    BigDecimal amount;
    BigDecimal balance;
}

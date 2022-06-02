package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.AccountType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountVo {
    String id;
    String accountNumber;
    AccountType accountType;
    BigDecimal initialBalance;
    Boolean status;
}

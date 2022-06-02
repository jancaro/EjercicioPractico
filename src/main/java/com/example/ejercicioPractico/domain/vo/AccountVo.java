package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountVo {
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private Boolean status;
}

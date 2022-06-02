package com.example.ejercicioPractico.domain.mappers;

import com.example.ejercicioPractico.domain.Account;
import com.example.ejercicioPractico.domain.enums.AccountType;
import com.example.ejercicioPractico.domain.vo.AccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Mapping(source = "accountType", target = "accountType", qualifiedByName = "accountEnum")
    public abstract Account toAccount(AccountVo accountVo);

    public abstract List<AccountVo> toAccountVoList(List<Account> accounts);

    public abstract AccountVo toAccountVo(Account account);

    @Named("accountEnum")
    protected AccountType accountEnum(String accountType) { return AccountType.valueOf(accountType); }
}

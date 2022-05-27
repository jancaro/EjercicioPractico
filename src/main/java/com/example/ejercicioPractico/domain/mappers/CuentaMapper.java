package com.example.ejercicioPractico.domain.mappers;

import com.example.ejercicioPractico.domain.Cuenta;
import com.example.ejercicioPractico.domain.enums.TipoCuenta;
import com.example.ejercicioPractico.domain.vo.CuentaVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CuentaMapper {

    @Mapping(source = "tipoCuenta", target = "tipoCuenta", qualifiedByName = "cuentaEnum")
    public abstract Cuenta toCuenta(CuentaVo cuentaVo);

    public abstract List<CuentaVo> toCuentaVoList(List<Cuenta> cuentas);

    public abstract CuentaVo toCuentaVo(Cuenta cuenta);

    @Named("cuentaEnum")
    protected TipoCuenta cuentaEnum(String tipoCuenta) { return TipoCuenta.valueOf(tipoCuenta); }
}

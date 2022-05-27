package com.example.ejercicioPractico.domain.mappers;

import com.example.ejercicioPractico.domain.Movimiento;
import com.example.ejercicioPractico.domain.enums.TipoMovimiento;
import com.example.ejercicioPractico.domain.vo.MovimientoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MovimientoMapper {
    @Mapping(source = "tipoMovimiento", target = "tipoMovimiento", qualifiedByName = "movimientoEnum")
    public abstract Movimiento toMovimiento(MovimientoVo movimientoVo);

    public abstract List<MovimientoVo> toMovimientoVoList(List<Movimiento> movimientos);

    public abstract MovimientoVo toMovimientoVo(Movimiento movimiento);

    @Named("movimientoEnum")
    protected TipoMovimiento cuentaEnum(String tipoMovimiento) { return TipoMovimiento.valueOf(tipoMovimiento); }
}

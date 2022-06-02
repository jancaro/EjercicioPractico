package com.example.ejercicioPractico.domain.mappers;

import com.example.ejercicioPractico.domain.Movement;
import com.example.ejercicioPractico.domain.enums.MovementType;
import com.example.ejercicioPractico.domain.vo.MovementVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MovementMapper {
    @Mapping(source = "movementType", target = "movementType", qualifiedByName = "movementEnum")
    public abstract Movement toMovement(MovementVo movementVo);

    public abstract List<MovementVo> toMovementVoList(List<Movement> movements);

    public abstract MovementVo toMovementVo(Movement movement);

    @Named("movementEnum")
    protected MovementType movementEnum(String movementType) { return MovementType.valueOf(movementType); }
}

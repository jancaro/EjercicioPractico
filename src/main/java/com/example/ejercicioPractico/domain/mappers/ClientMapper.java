package com.example.ejercicioPractico.domain.mappers;

import com.example.ejercicioPractico.domain.Client;
import com.example.ejercicioPractico.domain.enums.Gender;
import com.example.ejercicioPractico.domain.vo.ClientVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderEnum")
    public abstract Client toClient(ClientVo clientVo);

    public abstract List<ClientVo> toClientVoList(List<Client> clients);

    public abstract ClientVo toClientVo(Client client);

    @Named("genderEnum")
    protected Gender genderEnum(String gender) { return Gender.valueOf(gender);}
}

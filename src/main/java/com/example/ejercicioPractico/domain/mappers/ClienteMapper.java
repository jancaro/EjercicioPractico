package com.example.ejercicioPractico.domain.mappers;

import com.example.ejercicioPractico.domain.Cliente;
import com.example.ejercicioPractico.domain.enums.Genero;
import com.example.ejercicioPractico.domain.vo.ClienteVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {
    @Mapping(source = "genero", target = "genero", qualifiedByName = "generoEnum")
    public abstract Cliente toCliente(ClienteVo clienteVo);

    public abstract List<ClienteVo> toClienteVoList(List<Cliente> clientes);

    public abstract ClienteVo toClienteVo(Cliente cliente);

    @Named("generoEnum")
    protected Genero generoEnum(String genero) { return Genero.valueOf(genero);}
}

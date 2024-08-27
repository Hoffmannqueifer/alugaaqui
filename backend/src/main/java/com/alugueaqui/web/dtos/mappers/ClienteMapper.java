package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Cliente;
import com.alugueaqui.web.dtos.creates.ClienteCreateDto;
import com.alugueaqui.web.dtos.responses.ClienteResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto createDto) {
        return new ModelMapper().map(createDto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente Cliente) {
        return new ModelMapper().map(Cliente, ClienteResponseDto.class);
    }
}

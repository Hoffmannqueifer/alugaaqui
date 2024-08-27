package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Endereco;
import com.alugueaqui.web.dtos.creates.EnderecoCreateDto;
import com.alugueaqui.web.dtos.responses.EnderecoResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoCreateDto createDto) {
        return new ModelMapper().map(createDto, Endereco.class);
    }

    public static EnderecoResponseDto toDto(Endereco Endereco) {
        return new ModelMapper().map(Endereco, EnderecoResponseDto.class);
    }
}

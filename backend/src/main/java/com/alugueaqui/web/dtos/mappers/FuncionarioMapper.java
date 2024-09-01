package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.web.dtos.creates.FuncionarioCreateDto;
import com.alugueaqui.web.dtos.responses.FuncionarioResponseDto;
import com.alugueaqui.entities.Funcionario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FuncionarioMapper {

    public static Funcionario toFuncionario(FuncionarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Funcionario.class);
    }

//    public static FuncionarioResponseDto toDto(Funcionario funcionario) {
//        return new ModelMapper().map(funcionario, FuncionarioResponseDto.class);
//    }

    public static FuncionarioResponseDto toDto(Funcionario funcionario) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Funcionario.class, FuncionarioResponseDto.class)
                .addMapping(Funcionario::getUsuario, FuncionarioResponseDto::setUsuario);

        return modelMapper.map(funcionario, FuncionarioResponseDto.class);
    }
}

package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Veiculo;
import com.alugueaqui.web.dtos.creates.VeiculoCreateDto;
import com.alugueaqui.web.dtos.responses.VeiculoResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VeiculoMapper {

    public static Veiculo toVeiculo(VeiculoCreateDto createDto) {
        return new ModelMapper().map(createDto, Veiculo.class);
    }

    public static VeiculoResponseDto toDto(Veiculo Veiculo) {
        return new ModelMapper().map(Veiculo, VeiculoResponseDto.class);
    }
}

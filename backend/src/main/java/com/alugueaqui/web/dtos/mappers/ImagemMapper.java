package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Imagem;
import com.alugueaqui.web.dtos.creates.ImagemCreateDto;
import com.alugueaqui.web.dtos.responses.ImagemResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImagemMapper {

    public static Imagem toImagem(ImagemCreateDto createDto) {
        return new ModelMapper().map(createDto, Imagem.class);
    }

    public static ImagemResponseDto toDto(Imagem imagem) {
        return new ModelMapper().map(imagem, ImagemResponseDto.class);
    }

    public static List<Imagem> toImagens(List<ImagemCreateDto> imagens) {
        return imagens.stream()
                .map(ImagemMapper::toImagem)
                .collect(Collectors.toList());
    }
}

package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Postagem;
import com.alugueaqui.web.dtos.creates.PostagemCreateDto;
import com.alugueaqui.web.dtos.responses.PostagemResponseDto;
import org.modelmapper.ModelMapper;

public class PostagemMapper {

    public static Postagem toPostagem(PostagemCreateDto createDto) {
        return new ModelMapper().map(createDto, Postagem.class);
    }

    public static PostagemResponseDto toDto(Postagem postagem) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Postagem.class, PostagemResponseDto.class)
                .addMapping(Postagem::getImagens, PostagemResponseDto::setImagens)
                .addMapping(Postagem::getPagamentos, PostagemResponseDto::setPagamento);

        return modelMapper.map(postagem, PostagemResponseDto.class);
    }
}

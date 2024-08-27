package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Item;
import com.alugueaqui.web.dtos.creates.ItemCreateDto;
import com.alugueaqui.web.dtos.responses.ItemResponseDto;
import org.modelmapper.ModelMapper;

public class ItemMapper {

    public static Item toItem(ItemCreateDto createDto) {
        return new ModelMapper().map(createDto, Item.class);
    }

    public static ItemResponseDto toDto(Item item) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Item.class, ItemResponseDto.class)
                .addMapping(Item::getVeiculo, ItemResponseDto::setVeiculo)
                .addMapping(Item::getEndereco, ItemResponseDto::setEndereco);

        return modelMapper.map(item, ItemResponseDto.class);
    }
}

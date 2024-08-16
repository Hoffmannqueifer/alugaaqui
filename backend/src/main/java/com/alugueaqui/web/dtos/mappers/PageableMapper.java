package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.web.dtos.PageableDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageableDto toDto(Page page){
        return new ModelMapper().map(page, PageableDto.class);
    }
}

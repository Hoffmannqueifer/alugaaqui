package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.web.dtos.PageableDto;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageableDto toDto(Page<?> page) {
        PageableDto dto = new PageableDto();

        dto.setContent(page.getContent());
        dto.setFirst(page.isFirst());
        dto.setLast(page.isLast());
        dto.setNumber(page.getNumber());
        dto.setSize(page.getSize());
        dto.setNumberOfElements(page.getNumberOfElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setTotalElements((int) page.getTotalElements());

        return dto;
    }
}
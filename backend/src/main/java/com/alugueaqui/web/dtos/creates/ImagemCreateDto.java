package com.alugueaqui.web.dtos.creates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter  @NoArgsConstructor @AllArgsConstructor
public class ImagemCreateDto {
    private byte[] imageData;
    private String imageUrl;
}

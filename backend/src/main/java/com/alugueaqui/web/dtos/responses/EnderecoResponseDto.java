package com.alugueaqui.web.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EnderecoResponseDto {

    private Long id;
    private String logradouro;
    private String complemento;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}

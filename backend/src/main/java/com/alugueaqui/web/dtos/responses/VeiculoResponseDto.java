package com.alugueaqui.web.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VeiculoResponseDto {

    private Long id;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private Double kilometragem;
    private String cor;
    private String tipoCombustivel;
    private Integer numPortas;
    private Long veiculoCategoriaId;
}

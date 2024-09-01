package com.alugueaqui.web.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PagamentoPostagemResponseDto {

    private Long id;
    private LocalDateTime data;
    private Double valor;
    private String pagamentoTipo;
    private String status;
    private String observacao;
}

package com.alugueaqui.web.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PostagemResponseDto {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer statusRegistro;
    private ClienteResponseDto cliente;
    private List<ImagemResponseDto> imagens;
    private List<PagamentoPostagemResponseDto> pagamento;
}

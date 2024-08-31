package com.alugueaqui.web.dtos.creates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter  @NoArgsConstructor @AllArgsConstructor
public class PostagemCreateDto {
    private String titulo;
    private String descricao;
    private Long itemId;
    private String clienteCpf;
    private LocalDate dataFim;
    private List<ImagemCreateDto> imagens;
    private List<PagamentoPostagemCreateDto> pagamento;

}

package com.alugueaqui.web.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ItemResponseDto {

    private Long id;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorParcela;
    private Integer quantidadeParcelas;
    private Double valorTotalParcelado;
    private String negociaoTipo;
    private String estadoItemTipo;
    private String observacao;
    private Integer statusRegistro;
    private String itemNegociadoTipo;
    private VeiculoResponseDto veiculo;
    private EnderecoResponseDto endereco;
}

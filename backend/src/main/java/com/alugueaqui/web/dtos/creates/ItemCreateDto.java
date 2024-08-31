package com.alugueaqui.web.dtos.creates;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ItemCreateDto {

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;

    @NotNull(message = "Valor unitário é obrigatório")
    private Double valorUnitario;

    private Double valorParcela;
    private Integer quantidadeParcelas;
    private Double valorTotalParcelado;

    @NotBlank(message = "Tipo de item negociado é obrigatório")
    private String itemNegociadoTipo;

    @NotBlank(message = "Negociacao tipo é obrigatório")
    private String negociaoTipo;

    @NotBlank(message = "Estado do item é obrigatório")
    private String estadoItemTipo;

    @Column(name = "observacao")
    private String observacao;

    @Valid
    private VeiculoCreateDto veiculo;

    @Valid
    private EnderecoCreateDto endereco;

}

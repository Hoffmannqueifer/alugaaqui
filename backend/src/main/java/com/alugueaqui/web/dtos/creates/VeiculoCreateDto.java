package com.alugueaqui.web.dtos.creates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VeiculoCreateDto {

    @NotBlank(message = "Marca é obrigatória")
    @Size(min = 3, max = 50)
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(min = 3, max = 100)
    private String modelo;

    private Integer anoFabricacao;

    private Double kilometragem;

    private String cor;

    private String tipoCombustivel;

    private Integer numPortas;

    @NotNull(message = "Categoria do veículo é obrigatória")
    private Long veiculoCategoriaId;  // ID da categoria do veículo

}

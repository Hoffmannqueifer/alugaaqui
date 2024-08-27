package com.alugueaqui.web.dtos.creates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EnderecoCreateDto {

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 3, max = 100)
    private String logradouro;

    @Size(max = 300)
    private String complemento;

    @Positive
    private Integer numero;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 50)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 3, max = 50)
    private String cidade;

    @NotBlank( message = "Estado é obrigatório")
    @Size(min = 2, max = 2)
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;
}

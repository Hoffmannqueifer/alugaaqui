package com.alugueaqui.web.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FuncionarioCreateDto {

    @NotBlank
    @Size(min = 5, max = 200)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;
    @NotBlank
    @Size(min = 11, max = 11)
    private String celular;
    @Valid
    private UsuarioCreateDto usuario;
}

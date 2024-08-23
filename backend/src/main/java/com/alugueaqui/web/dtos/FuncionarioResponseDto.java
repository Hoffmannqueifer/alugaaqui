package com.alugueaqui.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FuncionarioResponseDto {

    private Long id;
    private String nome;
    private String cpf;
    private String celular;
    private String email;
    private UsuarioResponseDto usuario;
}

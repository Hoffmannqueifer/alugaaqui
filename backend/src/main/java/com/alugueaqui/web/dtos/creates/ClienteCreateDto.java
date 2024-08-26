package com.alugueaqui.web.dtos.creates;

import com.alugueaqui.util.ConstantesBD;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteCreateDto {


    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @CPF(message = "CPF inválido")
    private String cpf;
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    private String celular;

    @Column(name = "stregistro")
    private Integer statusRegistro = ConstantesBD.ativo;

}

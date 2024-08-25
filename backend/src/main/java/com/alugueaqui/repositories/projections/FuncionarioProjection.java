package com.alugueaqui.repositories.projections;

public interface FuncionarioProjection {

    Long getId();
    String getNome();
    String getCpf();
    String getCelular();
    String getEmail();
    Integer getStatusRegistro();
}

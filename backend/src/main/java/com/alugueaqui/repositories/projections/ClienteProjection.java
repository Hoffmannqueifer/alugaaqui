package com.alugueaqui.repositories.projections;

public interface ClienteProjection {

    Long getId();
    String getNome();
    String getCpf();
    String getCelular();
    String getEmail();
    Integer getStatusRegistro();
}

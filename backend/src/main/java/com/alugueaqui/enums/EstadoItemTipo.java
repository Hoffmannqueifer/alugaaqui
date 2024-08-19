package com.alugueaqui.enums;

public enum EstadoItemTipo {

    NOVO(1, "Novo"), USADO(2, "Usado");

    private int value;
    private String descricao;

    private EstadoItemTipo(int value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public int getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoItemTipo fromValue(int value) {
        for (EstadoItemTipo tipo : EstadoItemTipo.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo do estado do imóvel é inválido: " + value);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
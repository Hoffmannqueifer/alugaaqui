package com.alugueaqui.enums;

public enum NegociacaoTipo {

    A_VISTA(1, "AVISTA"),
    FINANCIADO(2, "FINANCIADO"),
    CONSORCIO(3, "CONSORCIO"),
    ALUGUEL(4, "ALUGUEL");

    private int value;
    private String descricao;

    private NegociacaoTipo(int value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public int getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

    public static NegociacaoTipo fromValue(int value) {
        for (NegociacaoTipo tipo : NegociacaoTipo.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Condição de venda inválida: " + value);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
package com.alugueaqui.enums;

public enum CondicaoVendaTipo {

    A_VISTA(1, "Avista"), FINANCIADO(2, "Financiado"), CONSORCIO(3, "Consórcio");

    private int value;
    private String descricao;

    private CondicaoVendaTipo(int value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public int getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CondicaoVendaTipo fromValue(int value) {
        for (CondicaoVendaTipo tipo : CondicaoVendaTipo.values()) {
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
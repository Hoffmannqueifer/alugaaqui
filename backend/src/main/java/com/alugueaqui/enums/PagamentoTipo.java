package com.alugueaqui.enums;

public enum PagamentoTipo {
    PIX(1, "PIX"),
    CARTAO_CREDITO(2, "CARTAO DE CREDITO"),
    CARTAO_DEBITO(3, "CARTAO DE DEBITO"),
    BOLETO(4, "BOLERO BANCARIO"),
    DINHEIRO(5, "DIHEIRO"),
    TRANF_BANCARIA(6, "TRANSFERENCIA BANCARIA");

    private int value;
    private String descricao;

    private PagamentoTipo(int value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public int getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PagamentoTipo fromValue(int value) {
        for (PagamentoTipo tipo : PagamentoTipo.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de pagamento inválido: " + value);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
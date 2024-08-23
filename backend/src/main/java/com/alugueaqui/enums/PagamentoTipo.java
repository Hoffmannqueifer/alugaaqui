package com.alugueaqui.enums;

public enum PagamentoTipo {
    PIX(1, "Pix"),
    CARTAO_CREDITO(2, "Cartão de Crédito"),
    CARTAO_DEBITO(3, "Cartão de Debito"),
    BOLETO(4, "Boleto"),
    DINHEIRO(5, "Dinheiro"),
    TRANF_BANCARIA(6, "Tranferência Bancária");

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
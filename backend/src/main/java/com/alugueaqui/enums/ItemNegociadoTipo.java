package com.alugueaqui.enums;

public enum ItemNegociadoTipo {
    IMOVEL(1, "Imóvel"),
    VEICULO(2, "Veículo"),
    OBJETOS(3, "Objetos"),
    SERVICOS(4, "Serviços"),
    OUTROS(5, "Outros");

    private int value;
    private String descricao;

    private ItemNegociadoTipo(int value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public int getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ItemNegociadoTipo fromValue(int value) {
        for (ItemNegociadoTipo tipo : ItemNegociadoTipo.values()) {
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
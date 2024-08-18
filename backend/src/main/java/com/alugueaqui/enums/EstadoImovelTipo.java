package com.alugueaqui.enums;

public enum EstadoImovelTipo {

    NOVO(1), USADO(2);

    private int value;

    private EstadoImovelTipo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

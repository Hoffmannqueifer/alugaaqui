package com.alugueaqui.enums;

public enum CondicaoVendaTipo {

    A_VISTA(1), FINANCIADO(2), CONSORCIO(3);

    private int value;

    private CondicaoVendaTipo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
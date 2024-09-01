package com.alugueaqui.util;

public class StringUtils {
    public static String getStringNumerico (String entrada) {
        if (entrada == null) {
            return null;
        }
        return entrada.replaceAll("[^0-9]", "");
    }

}

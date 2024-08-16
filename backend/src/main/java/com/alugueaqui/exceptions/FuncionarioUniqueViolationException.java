package com.alugueaqui.exceptions;

public class FuncionarioUniqueViolationException extends RuntimeException {
    public FuncionarioUniqueViolationException(String message) {
        super(message);
    }
}

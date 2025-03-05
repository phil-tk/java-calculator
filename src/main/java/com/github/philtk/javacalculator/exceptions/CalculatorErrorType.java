package com.github.philtk.javacalculator.exceptions;

public enum CalculatorErrorType {
    SYNTAX_ERROR("Error: Syntax"),
    DIVISION_BY_ZERO("Error: Division by 0");

    private final String message;

    CalculatorErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static CalculatorErrorType fromException(final CalculatorException e) {
        if (e instanceof DivisionByZeroException) {
            return DIVISION_BY_ZERO;
        }
        if (e instanceof SyntaxErrorException) {
            return SYNTAX_ERROR;
        } throw new CalculatorException("Impossible Error.");
    }
}

package com.github.philtk.javacalculator.exceptions;

/**
 * Enum representing different types of calculator errors.
 * Each error type has a corresponding error message.
 *
 * @author Phil Winkel
 */
public enum CalculatorErrorType {
    SYNTAX_ERROR("Error: Syntax"),
    DIVISION_BY_ZERO("Error: Division by 0");

    private final String message;

    /**
     * Constructor for the CalculatorErrorType enum.
     *
     * @param message the error message associated with the error type.
     */
    CalculatorErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Maps a CalculatorException to its corresponding CalculatorErrorType.
     *
     * @param e the CalculatorException to map.
     * @return the corresponding CalculatorErrorType.
     * @throws CalculatorException if the exception type is not recognized.
     */
    public static CalculatorErrorType fromException(final CalculatorException e) {
        if (e instanceof DivisionByZeroException) {
            return DIVISION_BY_ZERO;
        }
        if (e instanceof SyntaxErrorException) {
            return SYNTAX_ERROR;
        }
        throw new CalculatorException("Impossible Error.");
    }
}

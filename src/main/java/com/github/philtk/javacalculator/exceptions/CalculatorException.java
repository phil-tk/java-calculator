package com.github.philtk.javacalculator.exceptions;

/**
 * Custom exception for general calculator errors.
 * This class extends RuntimeException to allow unchecked exceptions for calculator errors.
 *
 * @author Phil Winkel
 */
public class CalculatorException extends RuntimeException {

    /**
     * Constructor for the CalculatorException class.
     *
     * @param message the error message for the exception.
     */
    public CalculatorException(final String message) {
        super(message);
    }
}

package com.github.philtk.javacalculator.exceptions;

/**
 * Custom exception thrown when a division by zero occurs in the calculator.
 *
 * @author Phil Winkel
 */
public class DivisionByZeroException extends CalculatorException {

    /**
     * Constructor for the DivisionByZeroException.
     * Initializes the exception with an empty message.
     */
    public DivisionByZeroException() {
        super("Division by zero is not allowed.");
    }
}

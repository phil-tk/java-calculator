package com.github.philtk.javacalculator.exceptions;

/**
 * Custom exception thrown when a syntax error is encountered in the calculator.
 *
 * @author Phil Winkel
 */
public class SyntaxErrorException extends CalculatorException {

    /**
     * Constructor for the SyntaxErrorException.
     * Initializes the exception with an empty message.
     */
    public SyntaxErrorException() {
        super("Syntax error in the input.");
    }
}

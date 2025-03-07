package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.exceptions.CalculatorErrorType;
import com.github.philtk.javacalculator.utils.InputType;

/**
 * Factory class responsible for creating different types of tokens based on input type.
 * The factory method ensures that the appropriate token is created based on the input type category.
 *
 * @author Phil Winkel
 */
public class TokenFactory {

    /**
     * Creates a token based on the provided InputType.
     *
     * @param type the InputType which determines the type of token to be created
     * @return a Token object of the corresponding type
     * @throws UnsupportedOperationException if the InputType category is CONTROL, ERROR, or ANSWER
     */
    public static Token createToken(final InputType type) {
        return switch (type.getCategory()) {
            case NUMBER -> new NumberToken(type);
            case OPERATOR -> new OperatorToken(type);
            case CONTROL -> throw new UnsupportedOperationException("Control elements do not create tokens.");
            case ERROR -> throw new UnsupportedOperationException("ERROR elements do not create tokens with InputType.");
            case ANSWER -> throw new UnsupportedOperationException("ANSWER elements do not create tokens with InputType.");
        };
    }

    /**
     * Creates an ErrorToken based on the provided CalculatorErrorType.
     *
     * @param calculatorErrorType the type of error to be represented as a token
     * @return an ErrorToken representing the provided error type
     */
    public static ErrorToken createToken(final CalculatorErrorType calculatorErrorType) {
        return new ErrorToken(calculatorErrorType);
    }
}

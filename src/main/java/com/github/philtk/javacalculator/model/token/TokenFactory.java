package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.exceptions.CalculatorErrorType;
import com.github.philtk.javacalculator.utils.InputType;

public class TokenFactory {
    public static Token createToken(final InputType type) {
        return switch (type.getCategory()) {
            case NUMBER -> new NumberToken(type);
            case OPERATOR -> new OperatorToken(type);
            case CONTROL -> throw new UnsupportedOperationException("Control elements do not create tokens.");
            case ERROR -> throw new UnsupportedOperationException("ERROR elements do not create tokens with InputType.");
            case ANSWER -> throw new UnsupportedOperationException("ANSWER elements do not create tokens with InputType.");
        };
    }

    public static ErrorToken createToken(final CalculatorErrorType calculatorErrorType) {
        return new ErrorToken(calculatorErrorType);
    }
}
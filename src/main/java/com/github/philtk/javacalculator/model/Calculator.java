package com.github.philtk.javacalculator.model;

import com.github.philtk.javacalculator.exceptions.CalculatorException;
import com.github.philtk.javacalculator.model.expression.ExpressionParser;
import com.github.philtk.javacalculator.model.token.Token;

import java.util.List;

public class Calculator {
    public static Value calculate(final List<Token> tokens) throws CalculatorException {
        if (tokens.isEmpty()) {
            throw new CalculatorException("Keine Eingabe zur Berechnung.");
        }
        return ExpressionParser.parseExpression(tokens).evaluate();
    }
}

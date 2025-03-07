package com.github.philtk.javacalculator.model;

import com.github.philtk.javacalculator.exceptions.CalculatorException;
import com.github.philtk.javacalculator.model.expression.ExpressionParser;
import com.github.philtk.javacalculator.model.token.Token;

import java.util.List;

/**
 * The Calculator class is responsible for evaluating mathematical expressions.
 * It takes a list of tokens representing an expression and calculates the result.
 *
 * @author Phil Winkel
 */
public class Calculator {

    /**
     * Calculates the result of a mathematical expression based on the provided tokens.
     *
     * @param tokens The list of tokens representing a mathematical expression.
     * @return The result of the calculation as a Value object.
     * @throws CalculatorException If the input list of tokens is empty, or if there are errors during parsing or evaluation.
     */
    public static Value calculate(final List<Token> tokens) throws CalculatorException {
        if (tokens.isEmpty()) {
            throw new CalculatorException("No input provided for calculation.");
        }
        return ExpressionParser.parseExpression(tokens).evaluate();
    }
}

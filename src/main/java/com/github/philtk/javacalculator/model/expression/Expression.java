package com.github.philtk.javacalculator.model.expression;

import com.github.philtk.javacalculator.model.Value;

/**
 * Represents an abstract mathematical expression.
 * Subclasses must implement the evaluate method.
 */
public abstract class Expression {
    /**
     * Evaluates the expression and returns the computed value.
     *
     * @return The result of the expression as a {@link Value}.
     */
    public abstract Value evaluate();
}
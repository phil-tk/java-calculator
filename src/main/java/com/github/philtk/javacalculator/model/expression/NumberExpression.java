package com.github.philtk.javacalculator.model.expression;

import com.github.philtk.javacalculator.model.Value;

/**
 * Represents a numerical expression containing a single value.
 */
class NumberExpression extends Expression {
    private final Value value;

    /**
     * Constructs a NumberExpression with a given value.
     *
     * @param value The numerical value (must not be null).
     * @throws IllegalArgumentException if value is null.
     */
    public NumberExpression(final Value value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        this.value = value;
    }

    /**
     * Evaluates the expression and returns its numerical value.
     *
     * @return The numerical value as a {@link Value}.
     */
    @Override
    public Value evaluate() {
        return value;
    }
}
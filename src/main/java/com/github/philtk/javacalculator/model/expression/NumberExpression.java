package com.github.philtk.javacalculator.model.expression;

import com.github.philtk.javacalculator.model.Value;

class NumberExpression extends Expression {
    private final Value value;

    public NumberExpression(final Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate() {
        return value;
    }
}
package com.github.philtk.javacalculator.model.expression;

import com.github.philtk.javacalculator.exceptions.DivisionByZeroException;
import com.github.philtk.javacalculator.model.Value;
import com.github.philtk.javacalculator.utils.InputType;

public class BinaryExpression extends Expression {
    private final Expression left;
    private final Expression right;
    private final InputType operator;

    public BinaryExpression(final Expression left, final Expression right, final InputType operator) {
        super();
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value evaluate() {
        Value leftValue = left.evaluate();
        Value rightValue = right.evaluate();

        if(operator == InputType.DIVIDE && rightValue.equals(new Value("0"))) {
            throw new DivisionByZeroException();
        }

        return switch (operator) {
            case ADD -> leftValue.add(rightValue);
            case SUBTRACT -> leftValue.subtract(rightValue);
            case MULTIPLY -> leftValue.multiply(rightValue);
            case DIVIDE -> leftValue.divide(rightValue);
            default -> throw new UnsupportedOperationException("Unknown operator.");
        };
    }
}

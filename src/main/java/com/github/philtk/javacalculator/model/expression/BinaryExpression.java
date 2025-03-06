package com.github.philtk.javacalculator.model.expression;

import com.github.philtk.javacalculator.exceptions.DivisionByZeroException;
import com.github.philtk.javacalculator.model.Value;
import com.github.philtk.javacalculator.utils.InputType;

/**
 * Represents a binary expression consisting of two operands and an operator.
 * Supports addition, subtraction, multiplication, and division.
 */
public class BinaryExpression extends Expression {
    private final Expression left;
    private final Expression right;
    private final InputType operator;

    /**
     * Constructs a binary expression with a left operand, a right operand, and an operator.
     *
     * @param left     The left operand (must not be null).
     * @param right    The right operand (must not be null).
     * @param operator The operator (must be a valid arithmetic operator).
     * @throws IllegalArgumentException if any parameter is null.
     */
    public BinaryExpression(final Expression left, final Expression right, final InputType operator) {
        super();
        if (left == null || right == null || operator == null) {
            throw new IllegalArgumentException("Operands and operator must not be null.");
        }
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /**
     * Evaluates the binary expression and returns the computed value.
     *
     * @return The result of the expression as a {@link Value}.
     * @throws DivisionByZeroException        If a division by zero is attempted.
     * @throws UnsupportedOperationException If the operator is not recognized.
     */
    @Override
    public Value evaluate() {
        Value leftValue = left.evaluate();
        Value rightValue = right.evaluate();

        if (operator == InputType.DIVIDE && rightValue.equals(new Value("0"))) {
            throw new DivisionByZeroException();
        }

        return switch (operator) {
            case ADD -> leftValue.add(rightValue);
            case SUBTRACT -> leftValue.subtract(rightValue);
            case MULTIPLY -> leftValue.multiply(rightValue);
            case DIVIDE -> leftValue.divide(rightValue);
            default -> throw new UnsupportedOperationException("Unknown operator: " + operator);
        };
    }
}

package com.github.philtk.javacalculator.model;

import com.github.philtk.javacalculator.model.token.NumberToken;
import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.utils.InputTypeUtil;
import com.github.philtk.javacalculator.exceptions.DivisionByZeroException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 * The Value class represents a numerical value with high precision. It provides methods for
 * performing arithmetic operations like addition, subtraction, multiplication, and division.
 * The precision is handled internally with different contexts for internal and external precision.
 */
public class Value {

    // The internal precision for calculations (e.g., for intermediate results).
    private static final int INTERNAL_PRECISION = 20;

    // The external precision for displaying the result.
    private static final int EXTERNAL_PRECISION = 10;

    // The BigDecimal value that holds the actual numerical value.
    private final BigDecimal bigDecimal;

    // MathContext for internal precision (used for intermediate calculations).
    private static final MathContext innerContext = new MathContext(INTERNAL_PRECISION, RoundingMode.HALF_UP);

    // MathContext for external precision (used for final result display).
    private static final MathContext exteriorContext = new MathContext(EXTERNAL_PRECISION, RoundingMode.HALF_UP);

    /**
     * Constructor that initializes a Value object based on a string representation of a number.
     *
     * @param value The string representation of the number.
     */
    public Value(final String value) {
        bigDecimal = new BigDecimal(value).round(innerContext);
    }

    /**
     * Private constructor that initializes a Value object based on a BigDecimal value.
     *
     * @param bigDecimal The BigDecimal representation of the number.
     */
    private Value(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    /**
     * Adds another Value to the current Value and returns the result as a new Value.
     *
     * @param value The Value to be added.
     * @return The result of the addition as a new Value.
     */
    public Value add(final Value value) {
        return new Value(bigDecimal.add(value.bigDecimal, innerContext));
    }

    /**
     * Subtracts another Value from the current Value and returns the result as a new Value.
     *
     * @param value The Value to be subtracted.
     * @return The result of the subtraction as a new Value.
     */
    public Value subtract(final Value value) {
        return new Value(bigDecimal.subtract(value.bigDecimal, innerContext));
    }

    /**
     * Multiplies the current Value with another Value and returns the result as a new Value.
     *
     * @param value The Value to be multiplied.
     * @return The result of the multiplication as a new Value.
     */
    public Value multiply(final Value value) {
        return new Value(bigDecimal.multiply(value.bigDecimal, innerContext));
    }

    /**
     * Divides the current Value by another Value and returns the result as a new Value.
     * If division by zero is attempted, a CalculatorException is thrown.
     *
     * @param value The Value to divide by.
     * @return The result of the division as a new Value.
     * @throws DivisionByZeroException If division by zero is attempted.
     */
    public Value divide(final Value value) throws DivisionByZeroException {
        if (value.bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        return new Value(bigDecimal.divide(value.bigDecimal, INTERNAL_PRECISION, RoundingMode.HALF_UP));
    }

    /**
     * Compares the current Value with another Value to check if they are equal.
     *
     * @param value The Value to compare with.
     * @return true if the two values are equal, false otherwise.
     */
    public boolean equals(final Value value) {
        return bigDecimal.compareTo(value.bigDecimal) == 0;
    }

    /**
     * Converts the current Value to a NumberToken.
     * This method rounds the value to the external precision and strips trailing zeros.
     *
     * @return The NumberToken representing the current Value.
     */
    public NumberToken getNumberToken() {
        final BigDecimal roundedBigDecimal = bigDecimal.round(exteriorContext).stripTrailingZeros();

        final List<InputType> inputTypes = roundedBigDecimal.toPlainString()
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(InputTypeUtil::fromInternalText)
                .toList();

        final NumberToken numberToken = new NumberToken(inputTypes.get(0));
        inputTypes.stream().skip(1).forEach(numberToken::add);

        return numberToken;
    }
}

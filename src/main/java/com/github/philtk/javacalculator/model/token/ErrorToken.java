package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.exceptions.CalculatorErrorType;
import com.github.philtk.javacalculator.utils.Category;

/**
 * Represents an error token within the calculator.
 * Used to indicate an invalid state or computation error.
 */
public class ErrorToken extends StaticToken {
    private final CalculatorErrorType calculatorErrorType;

    /**
     * Constructs an ErrorToken with a specified error type.
     *
     * @param calculatorErrorType the type of calculator error
     */
    public ErrorToken(final CalculatorErrorType calculatorErrorType) {
        this.calculatorErrorType = calculatorErrorType;
    }

    @Override
    public Category getCategory() {
        return Category.ERROR;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getDisplayText() {
        return calculatorErrorType.getMessage();
    }
}
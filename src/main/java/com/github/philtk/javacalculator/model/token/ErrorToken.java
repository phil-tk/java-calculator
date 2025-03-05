package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.exceptions.CalculatorErrorType;
import com.github.philtk.javacalculator.utils.Category;

public class ErrorToken extends StaticToken{
    private final CalculatorErrorType calculatorErrorType;

    public ErrorToken(final CalculatorErrorType calculatorErrorType){
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

package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.Category;
import com.github.philtk.javacalculator.utils.InputType;

public class OperatorToken extends StaticToken {
    private final InputType operator;
    private static final Category CATEGORY = Category.OPERATOR;

    public OperatorToken(final InputType operator) {
        this.operator = operator;
    }

    public InputType getOperator(){
        return operator;
    }

    @Override
    public boolean isValid(){
        return true;
    }

    @Override
    public Category getCategory(){
        return CATEGORY;
    }

    @Override
    public String getDisplayText(){
        return operator.getDisplayTxt();
    }
}

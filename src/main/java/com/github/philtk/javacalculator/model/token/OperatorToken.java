package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.Category;
import com.github.philtk.javacalculator.utils.InputType;

/**
 * Represents an operator token within a mathematical expression.
 *
 * @author Phil Winkel
 */
public class OperatorToken extends StaticToken {
    private final InputType operator;
    private static final Category CATEGORY = Category.OPERATOR;

    /**
     * Constructs an OperatorToken with the given operator type.
     *
     * @param operator the mathematical operator
     */
    public OperatorToken(final InputType operator) {
        this.operator = operator;
    }

    /**
     * Retrieves the operator type of this token.
     *
     * @return the operator type
     */
    public InputType getOperator() {
        return operator;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Category getCategory() {
        return CATEGORY;
    }

    @Override
    public String getDisplayText() {
        return operator.getDisplayTxt();
    }
}


package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.token.NumberToken;
import com.github.philtk.javacalculator.model.token.Token;

import java.util.List;

public class ContextManager {
    private boolean resultDisplayed;

    public ContextManager() {
        this.resultDisplayed = false;
    }

    public void setResultDisplayed(final boolean displayed) {
        this.resultDisplayed = displayed;
    }

    public boolean shouldClearOnNextInput() {
        return resultDisplayed;
    }

    public InputType getUsableInputType(final InputType inputType, final List<Token> tokens, final EventType eventType) {
        if (eventType != EventType.KEY) {
            return inputType;
        }

        Category lastCategory = null;
        Token lastToken = null;

        if (!tokens.isEmpty()) {
            lastToken = tokens.getLast();
            lastCategory = lastToken.getCategory();
        }

        return determineInputType(inputType, lastCategory, lastToken);
    }

    private InputType determineInputType(final InputType inputType, final Category lastCategory, final Token lastToken) {
        if (inputType == InputType.SUBTRACT || inputType == InputType.SIGN) {
            if (shouldClearOnNextInput()) {
                return InputType.SUBTRACT;
            }
            if (lastCategory == null || lastCategory == Category.OPERATOR) {
                return InputType.SIGN;
            }
            if (lastToken instanceof NumberToken numberToken && numberToken.allowsSign()) {
                return InputType.SIGN;
            }
            return InputType.SUBTRACT;
        }
        return inputType;
    }
}
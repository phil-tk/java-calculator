package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.token.NumberToken;
import com.github.philtk.javacalculator.model.token.Token;

import java.util.List;

/**
 * Manages the context of input handling, ensuring appropriate behavior based on previous inputs.
 *
 * @author Phil Winkel
 */
public class ContextManager {
    private boolean resultDisplayed;

    /**
     * Initializes a new ContextManager with the result display set to false.
     */
    public ContextManager() {
        this.resultDisplayed = false;
    }

    /**
     * Sets whether the result is currently displayed.
     *
     * @param displayed true if a result is displayed, false otherwise
     */
    public void setResultDisplayed(final boolean displayed) {
        this.resultDisplayed = displayed;
    }

    /**
     * Determines whether the next input should clear the display.
     *
     * @return true if the display should be cleared on the next input, false otherwise
     */
    public boolean shouldClearOnNextInput() {
        return resultDisplayed;
    }

    /**
     * Determines the appropriate input type based on the event type and current token context.
     *
     * @param inputType The current input type
     * @param tokens The list of existing tokens
     * @param eventType The type of input event
     * @return The valid input type based on the current context
     * @throws IllegalArgumentException if inputType is null
     */
    public InputType getUsableInputType(final InputType inputType, final List<Token> tokens, final EventType eventType) {
        if (inputType == null) {
            throw new IllegalArgumentException("InputType cannot be null");
        }
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

    /**
     * Determines how to interpret subtraction or sign input based on the last token.
     *
     * @param inputType The given input type
     * @param lastCategory The category of the last token
     * @param lastToken The last token in the list
     * @return The corrected input type based on the context
     */
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

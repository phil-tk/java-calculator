package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.model.Value;
import com.github.philtk.javacalculator.utils.Category;
import com.github.philtk.javacalculator.utils.InputType;

/**
 * Represents an Answer token, typically holding the final result of a calculation.
 * It wraps a NumberToken and the corresponding value of the answer.
 *
 * @author Phil Winkel
 */
public class AnswerToken extends StaticToken {
    private final NumberToken numberToken;
    private final Value value;

    /**
     * Constructs an AnswerToken based on the provided answer value.
     *
     * @param answer the value of the answer to be wrapped in the token
     */
    public AnswerToken(final Value answer) {
        this.numberToken = answer.getNumberToken();
        this.value = answer;
    }

    /**
     * Default constructor that creates an AnswerToken with a zero value.
     */
    public AnswerToken() {
        this.numberToken = new NumberToken(InputType.ZERO);
        value = numberToken.getValue();
    }

    /**
     * Returns the category of this token, which is always ANSWER.
     *
     * @return the category of the token
     */
    @Override
    public Category getCategory() {
        return Category.ANSWER;
    }

    /**
     * Checks if the token is valid. For AnswerToken, it always returns true.
     *
     * @return true, as AnswerTokens are always valid
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * Returns the display text for this token, which is the answer label.
     *
     * @return the display text of the AnswerToken
     */
    @Override
    public String getDisplayText() {
        return InputType.ANSWER.getDisplayTxt();
    }

    /**
     * Returns the value associated with the answer token.
     *
     * @return the value of the answer
     */
    public Value getValue() {
        return value;
    }
}

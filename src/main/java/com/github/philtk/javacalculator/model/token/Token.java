package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.Category;

/**
 * Represents a generic token in the calculator.
 * Tokens are used to build expressions and define operations.
 *
 * @author Phil Winkel
 */
public interface Token {
    /**
     * Retrieves the category of the token.
     *
     * @return The category of the token.
     */
    Category getCategory();

    /**
     * Checks if the token is valid within an expression.
     *
     * @return {@code true} if the token is valid, otherwise {@code false}.
     */
    boolean isValid();

    /**
     * Gets the display text representation of the token.
     *
     * @return The display text as a string.
     */
    String getDisplayText();

    /**
     * Determines if the last input can be removed.
     *
     * @return {@code true} if removable, otherwise {@code false}.
     */
    boolean canRemoveLast();
}
package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.InputType;

/**
 * Represents a dynamic token that can change during input.
 * Dynamic tokens allow modification, such as appending or removing elements.
 */
public abstract class DynamicToken implements Token {
    /**
     * Adds an input type to the dynamic token.
     *
     * @param inputType The input type to be added.
     */
    public abstract void add(final InputType inputType);

    /**
     * Removes the last input from the dynamic token.
     */
    public abstract void removeLast();
}
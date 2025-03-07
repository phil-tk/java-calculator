package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.Category;

/**
 * Represents a static token that does not change dynamically during input.
 * Static tokens include operators and predefined symbols.
 *
 * @author Phil Winkel
 */
public abstract class StaticToken implements Token {

    @Override
    public abstract Category getCategory();

    @Override
    public abstract boolean isValid();

    @Override
    public abstract String getDisplayText();

    @Override
    public boolean canRemoveLast() {
        return false;
    }
}
package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.Category;

public abstract class StaticToken implements Token{
    public abstract Category getCategory();
    public abstract boolean isValid();
    public abstract String getDisplayText();
    @Override
    public boolean canRemoveLast() {
        return false;
    }
}

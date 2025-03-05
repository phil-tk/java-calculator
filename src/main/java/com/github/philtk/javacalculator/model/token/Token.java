package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.Category;

public interface Token {
    public Category getCategory();
    public boolean isValid();
    public String getDisplayText();
    boolean canRemoveLast();
}
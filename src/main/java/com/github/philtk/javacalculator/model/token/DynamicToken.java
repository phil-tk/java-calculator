package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.InputType;

public abstract class DynamicToken implements Token{
    public abstract void add(final InputType inputType);
    public abstract void removeLast();
}

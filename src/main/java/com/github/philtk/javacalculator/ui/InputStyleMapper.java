package com.github.philtk.javacalculator.ui;

import com.github.philtk.javacalculator.styles.ButtonStyle;
import com.github.philtk.javacalculator.utils.InputType;

public class InputStyleMapper {
    public static ButtonStyle getStyleFor(final InputType inputType) {
        return switch (inputType) {
            case ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DECIMAL, SIGN -> ButtonStyle.NUMBER_BLOCK;
            case ADD, SUBTRACT, MULTIPLY, DIVIDE, ANSWER, CLEAR, BACKSPACE -> ButtonStyle.TOP_RIGHT;
            case EQUAL -> ButtonStyle.EQUAL;
            default -> throw new IllegalArgumentException();
        };
    }
}


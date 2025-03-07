package com.github.philtk.javacalculator.ui;

import com.github.philtk.javacalculator.styles.ButtonStyle;
import com.github.philtk.javacalculator.utils.InputType;

/**
 * Maps calculator input types to their corresponding button styles.
 *
 * @author Phil Winkel
 */
public class InputStyleMapper {
    /**
     * Retrieves the appropriate button style for a given input type.
     *
     * @param inputType The type of input (e.g., number, operator, special function).
     * @return The corresponding ButtonStyle for the given input type.
     * @throws IllegalArgumentException if the input type is not recognized.
     */
    public static ButtonStyle getStyleFor(final InputType inputType) {
        return switch (inputType) {
            case ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DECIMAL, SIGN -> ButtonStyle.NUMBER_BLOCK;
            case ADD, SUBTRACT, MULTIPLY, DIVIDE, ANSWER, CLEAR, BACKSPACE -> ButtonStyle.TOP_RIGHT;
            case EQUAL -> ButtonStyle.EQUAL;
            default -> throw new IllegalArgumentException("Unsupported InputType: " + inputType);
        };
    }
}

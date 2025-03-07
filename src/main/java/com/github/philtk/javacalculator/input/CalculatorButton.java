package com.github.philtk.javacalculator.input;

import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.styles.ButtonStyle;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * Represents a calculator button with a specific style and functionality.
 * This class extends {@link Button} and integrates custom styling and input handling.
 *
 * @author Phil Winkel
 */
public class CalculatorButton extends Button {
    private final InputType inputType;

    /**
     * Creates a new calculator button with the specified style and input type.
     *
     * @param buttonStyle The style settings for the button.
     * @param inputType   The type of input this button represents.
     * @throws IllegalArgumentException if inputType is null.
     */
    public CalculatorButton(final ButtonStyle buttonStyle, final InputType inputType) {
        super(inputType.getButtonTxt());

        if (inputType == null) {
            throw new IllegalArgumentException("InputType cannot be null");
        }

        this.inputType = inputType;

        setFont(buttonStyle.getFont());
        setTextFill(buttonStyle.getFontColor());
        updateStyle(buttonStyle.getDefaultBackground());

        setOnMouseEntered(e -> updateStyle(buttonStyle.getHoverBackground()));
        setOnMouseExited(e -> updateStyle(buttonStyle.getDefaultBackground()));
        setOnMousePressed(e -> updateStyle(buttonStyle.getClickBackground()));
        setOnMouseReleased(e -> updateStyle(buttonStyle.getHoverBackground()));
    }

    /**
     * Returns the associated input type of this button.
     *
     * @return The {@link InputType} of this button.
     */
    @Override
    public Object getUserData() {
        return inputType;
    }

    /**
     * Updates the button's style based on the given background color.
     *
     * @param color The background color to apply.
     * @throws IllegalArgumentException if color is null.
     */
    private void updateStyle(final Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }

        setStyle("-fx-background-color: " + toRgbString(color) + ";"
                + "-fx-border-color: " + toRgbString(color.darker()) + ";"
                + "-fx-border-radius: 5px;"
                + "-fx-background-radius: 5px;"
                + "-fx-padding: 10 20;");
    }

    /**
     * Converts a {@link Color} object to an RGB string representation.
     *
     * @param color The color to convert.
     * @return The RGB string in the format "rgb(r, g, b)".
     * @throws IllegalArgumentException if color is null.
     */
    private String toRgbString(final Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }

        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}

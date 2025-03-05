package com.github.philtk.javacalculator.input;

import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.styles.ButtonStyle;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CalculatorButton extends Button {
    private final InputType inputType;

    public CalculatorButton(final ButtonStyle buttonStyle, final InputType inputType) {
        super(inputType.getButtonTxt());
        this.inputType = inputType;

        setFont(buttonStyle.getFont());
        setTextFill(buttonStyle.getFontColor());

        updateStyle(buttonStyle.getDefaultBackground());

        setOnMouseEntered(e -> updateStyle(buttonStyle.getHoverBackground()));
        setOnMouseExited(e -> updateStyle(buttonStyle.getDefaultBackground()));
        setOnMousePressed(e -> updateStyle(buttonStyle.getClickBackground()));
        setOnMouseReleased(e -> updateStyle(buttonStyle.getHoverBackground()));
    }

    @Override
    public Object getUserData(){
        return inputType;
    }

    private void updateStyle(final Color color) {
        setStyle("-fx-background-color: " + toRgbString(color) + ";"
                + "-fx-border-color: " + toRgbString(color.darker()) + ";"
                + "-fx-border-radius: 5px;"
                + "-fx-background-radius: 5px;"
                + "-fx-padding: 10 20;");
    }

    private String toRgbString(final Color color) {
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}

package com.github.philtk.javacalculator.styles;

import javafx.scene.paint.Color;

/**
 * Enum representing the color scheme used in the application.
 * Defines various colors for backgrounds, fonts, and interactive elements.
 *
 * @author Phil Winkel
 */
public enum AppColor {
    BACKGROUND(Color.rgb(32, 32, 32)),
    TEXT_PRIMARY(Color.WHITE),
    TEXT_SECONDARY(Color.rgb(160, 160, 160)),

    EQUAL_DEFAULT_BACKGROUND(Color.rgb(255, 179, 71)),
    EQUAL_HOVER_BACKGROUND(Color.rgb(240, 165, 65)),
    EQUAL_CLICK_BACKGROUND(Color.rgb(225, 150, 58)),
    EQUAL_FONT(Color.rgb(66, 44, 16)),

    NUMBER_DEFAULT_BACKGROUND(Color.rgb(59, 59, 59)),
    NUMBER_HOVER_BACKGROUND(Color.rgb(50, 50, 50)),
    NUMBER_CLICK_BACKGROUND(Color.rgb(40, 40, 40)),
    NUMBER_FONT(Color.WHITE),

    OPERATOR_DEFAULT_BACKGROUND(Color.rgb(50, 50, 50)),
    OPERATOR_HOVER_BACKGROUND(Color.rgb(59, 59, 59)),
    OPERATOR_CLICK_BACKGROUND(Color.rgb(40, 40, 40)),
    OPERATOR_FONT(Color.WHITE);

    private final Color color;

    /**
     * Constructor to associate a color with each enum value.
     *
     * @param color The color value.
     */
    AppColor(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
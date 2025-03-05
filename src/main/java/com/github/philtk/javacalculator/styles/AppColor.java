package com.github.philtk.javacalculator.styles;

import javafx.scene.paint.Color;

public enum AppColor {
    BACKGROUND(Color.rgb(32, 32, 32)),
    TEXT_PRIMARY(Color.WHITE),
    TEXT_SECONDARY(Color.rgb(160, 160, 160)),

    EQUAL_DEFAULT_BACKGROUND(Color.rgb(118, 185, 237)),
    EQUAL_HOVER_BACKGROUND(Color.rgb(109, 169, 216)),
    EQUAL_CLICK_BACKGROUND(Color.rgb(100, 154, 195)),
    EQUAL_FONT(Color.rgb(33, 52, 66)),

    NUMBER_DEFAULT_BACKGROUND(Color.rgb(59, 59, 59)),
    NUMBER_HOVER_BACKGROUND(Color.rgb(50, 50, 50)),
    NUMBER_CLICK_BACKGROUND(Color.rgb(40, 40, 40)),
    NUMBER_FONT(Color.WHITE),

    OPERATOR_DEFAULT_BACKGROUND(Color.rgb(50, 50, 50)),
    OPERATOR_HOVER_BACKGROUND(Color.rgb(59, 59, 59)),
    OPERATOR_CLICK_BACKGROUND(Color.rgb(40, 40, 40)),
    OPERATOR_FONT(Color.WHITE);

    private final Color color;

    AppColor(final Color color) {
        this.color = color;
    }

    public Color getColor() { return color; }
}


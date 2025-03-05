package com.github.philtk.javacalculator.styles;

import javafx.scene.text.Font;

public enum AppFont {
    BUTTON(Font.font("Latin Modern Math", 20)),
    INPUT_AREA(Font.font("Latin Modern Math", 47)),
    HISTORY_AREA(Font.font("Latin Modern Math", 20));

    private final Font font;

    AppFont(final Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}

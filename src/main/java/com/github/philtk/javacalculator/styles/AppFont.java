package com.github.philtk.javacalculator.styles;

import javafx.scene.text.Font;

/**
 * Enum representing font styles used in the application.
 *
 * @author Phil Winkel
 */
public enum AppFont {
    BUTTON(Font.font("Latin Modern Math", 20)),
    INPUT_AREA(Font.font("Latin Modern Math", 47)),
    HISTORY_AREA(Font.font("Latin Modern Math", 20));

    private final Font font;

    /**
     * Constructor to define font styles.
     *
     * @param font The font style.
     */
    AppFont(final Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
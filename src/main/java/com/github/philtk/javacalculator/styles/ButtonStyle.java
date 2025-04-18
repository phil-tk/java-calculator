package com.github.philtk.javacalculator.styles;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Enum representing different button styles in the calculator UI.
 * Defines background colors, font colors, and hover/click styles.
 *
 * @author Phil Winkel
 */
public enum ButtonStyle {
    TOP_RIGHT(AppColor.OPERATOR_DEFAULT_BACKGROUND, AppColor.OPERATOR_HOVER_BACKGROUND, AppColor.OPERATOR_CLICK_BACKGROUND, AppColor.OPERATOR_FONT, AppFont.BUTTON),
    NUMBER_BLOCK(AppColor.NUMBER_DEFAULT_BACKGROUND, AppColor.NUMBER_HOVER_BACKGROUND, AppColor.NUMBER_CLICK_BACKGROUND, AppColor.NUMBER_FONT, AppFont.BUTTON),
    EQUAL(AppColor.EQUAL_DEFAULT_BACKGROUND, AppColor.EQUAL_HOVER_BACKGROUND, AppColor.EQUAL_CLICK_BACKGROUND, AppColor.EQUAL_FONT, AppFont.BUTTON);

    private final Color defaultBackground;
    private final Color hoverBackground;
    private final Color clickBackground;
    private final Color fontColor;
    private final Font font;

    /**
     * Constructor to define button styles with background and font properties.
     *
     * @param defaultBackground Default background color.
     * @param hoverBackground Background color on hover.
     * @param clickBackground Background color on click.
     * @param fontColor Font color.
     * @param font Font style.
     */
    ButtonStyle(final AppColor defaultBackground, final AppColor hoverBackground, final AppColor clickBackground, final AppColor fontColor, final AppFont font) {
        this.defaultBackground = defaultBackground.getColor();
        this.hoverBackground = hoverBackground.getColor();
        this.clickBackground = clickBackground.getColor();
        this.fontColor = fontColor.getColor();
        this.font = font.getFont();
    }

    public Color getDefaultBackground() {
        return defaultBackground;
    }

    public Color getHoverBackground() {
        return hoverBackground;
    }

    public Color getClickBackground() {
        return clickBackground;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Font getFont() {
        return font;
    }
}

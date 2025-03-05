package com.github.philtk.javacalculator.utils;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public enum InputType {
    ZERO("0", "0", "0", Category.NUMBER, Set.of(KeyEvent.VK_0, KeyEvent.VK_NUMPAD0)),
    ONE("1", "1", "1", Category.NUMBER, Set.of(KeyEvent.VK_1, KeyEvent.VK_NUMPAD1)),
    TWO("2", "2", "2", Category.NUMBER, Set.of(KeyEvent.VK_2, KeyEvent.VK_NUMPAD2)),
    THREE("3", "3", "3", Category.NUMBER, Set.of(KeyEvent.VK_3, KeyEvent.VK_NUMPAD3)),
    FOUR("4", "4", "4", Category.NUMBER, Set.of(KeyEvent.VK_4, KeyEvent.VK_NUMPAD4)),
    FIVE("5", "5", "5", Category.NUMBER, Set.of(KeyEvent.VK_5, KeyEvent.VK_NUMPAD5)),
    SIX("6", "6", "6", Category.NUMBER, Set.of(KeyEvent.VK_6, KeyEvent.VK_NUMPAD6)),
    SEVEN("7", "7", "7", Category.NUMBER, Set.of(KeyEvent.VK_7, KeyEvent.VK_NUMPAD7)),
    EIGHT("8", "8", "8", Category.NUMBER, Set.of(KeyEvent.VK_8, KeyEvent.VK_NUMPAD8)),
    NINE("9", "9", "9", Category.NUMBER, Set.of(KeyEvent.VK_9, KeyEvent.VK_NUMPAD9)),

    DECIMAL(".", ",", ",", Category.NUMBER, Set.of(KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD, KeyEvent.VK_SEPARATOR)),
    SIGN("-", "(-)", "-", Category.NUMBER, Set.of(KeyEvent.VK_SUBTRACT, KeyEvent.VK_MINUS)),

    ADD(null, "+", "+", Category.OPERATOR, Set.of(KeyEvent.VK_ADD, KeyEvent.VK_PLUS)),
    SUBTRACT(null, "-", "-", Category.OPERATOR, Set.of(KeyEvent.VK_SUBTRACT, KeyEvent.VK_MINUS)),
    MULTIPLY(null, "×", "×", Category.OPERATOR, Set.of(KeyEvent.VK_MULTIPLY)),
    DIVIDE(null, "÷", "÷", Category.OPERATOR, Set.of(KeyEvent.VK_DIVIDE, KeyEvent.VK_SLASH)),

    ANSWER(null, "ans", "ans", Category.ANSWER, Set.of()),
    BACKSPACE(null, "⌫", "", Category.CONTROL, Set.of(KeyEvent.VK_BACK_SPACE)),
    EQUAL(null, "=", "=", Category.CONTROL, Set.of(KeyEvent.VK_ENTER)),
    CLEAR(null, "clear", "", Category.CONTROL, Set.of(KeyEvent.VK_DELETE));

    private final String internalText;
    private final String buttonTxt;
    private final String displayTxt;
    private final Category category;
    private final Set<Integer> keyCodes;

    InputType(final String internalText, final String buttonTxt, final String displayTxt,
              final Category category, final Set<Integer> keyCodes) {
        this.internalText = internalText;
        this.buttonTxt = buttonTxt;
        this.displayTxt = displayTxt;
        this.category = category;
        this.keyCodes = keyCodes;
    }

    public String getInternalText() { return internalText; }
    public String getButtonTxt() { return buttonTxt; }
    public String getDisplayTxt() { return displayTxt; }
    public Category getCategory() { return category; }
    public Set<Integer> getKeyCodes() { return keyCodes; }

    private static final Set<InputType> DIGITS = Set.of(
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
    );

    public boolean isDigit() {
        return DIGITS.contains(this);
    }
}
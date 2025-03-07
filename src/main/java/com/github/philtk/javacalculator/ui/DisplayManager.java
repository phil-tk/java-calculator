package com.github.philtk.javacalculator.ui;

import com.github.philtk.javacalculator.utils.HistoryManager;
import com.github.philtk.javacalculator.model.token.TokenManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Manages the display text of the calculator, including the input and history fields.
 *
 * @author Phil Winkel
 */
public class DisplayManager {
    private final TokenManager tokenManager;
    private final HistoryManager historyManager;
    private final StringProperty inputText = new SimpleStringProperty("");
    private final StringProperty historyText = new SimpleStringProperty("");

    /**
     * Constructs a DisplayManager with the given token and history managers.
     *
     * @param tokenManager   The manager handling tokenized input.
     * @param historyManager The manager handling calculation history.
     */
    public DisplayManager(final TokenManager tokenManager, final HistoryManager historyManager) {
        this.tokenManager = tokenManager;
        this.historyManager = historyManager;
    }

    /**
     * Gets the property for the input text.
     *
     * @return The StringProperty representing the input field text.
     */
    public StringProperty inputTextProperty() {
        return inputText;
    }

    /**
     * Gets the property for the history text.
     *
     * @return The StringProperty representing the history field text.
     */
    public StringProperty historyTextProperty() {
        return historyText;
    }

    /**
     * Updates the display text with the latest input and history values.
     */
    public void update() {
        inputText.set(tokenManager.getDisplayText());
        historyText.set(historyManager.getHistory());
    }
}
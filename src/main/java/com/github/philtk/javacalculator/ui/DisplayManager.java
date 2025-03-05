package com.github.philtk.javacalculator.ui;

import com.github.philtk.javacalculator.utils.HistoryManager;
import com.github.philtk.javacalculator.model.token.TokenManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DisplayManager {
    private final TokenManager tokenManager;
    private final HistoryManager historyManager;
    private final StringProperty inputText = new SimpleStringProperty("");
    private final StringProperty historyText = new SimpleStringProperty("");

    public DisplayManager(final TokenManager tokenManager, final HistoryManager historyManager) {
        this.tokenManager = tokenManager;
        this.historyManager = historyManager;
    }

    public StringProperty inputTextProperty() {
        return inputText;
    }

    public StringProperty historyTextProperty() {
        return historyText;
    }

    public void update() {
        inputText.set(tokenManager.getDisplayText());
        historyText.set(historyManager.getHistory());
    }
}
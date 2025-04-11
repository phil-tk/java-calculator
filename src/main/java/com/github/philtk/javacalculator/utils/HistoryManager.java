package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.token.AnswerToken;
import com.github.philtk.javacalculator.model.Value;

/**
 * Manages the calculation history and last answer storage.
 *
 * @author Phil Winkel
 */
public class HistoryManager {
    private String history = "";
    private AnswerToken lastAnswer = new AnswerToken();

    /**
     * Saves the provided text as the latest calculation history.
     *
     * @param text The text representing the last calculation.
     */
    public void saveHistory(final String text) {
        if (text == null) {
            throw new IllegalArgumentException("History text cannot be null");
        }
        history = text + " =";
    }

    /**
     * Retrieves the stored calculation history.
     *
     * @return The history as a string.
     */
    public String getHistory() {
        return history;
    }

    /**
     * Stores the last computed answer.
     *
     * @param answer The answer value to store.
     */
    public void saveAnswer(final Value answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null");
        }
        lastAnswer = new AnswerToken(answer);
    }

    /**
     * Retrieves the last stored answer.
     *
     * @return The last answer as an AnswerToken.
     */
    public AnswerToken getLastAnswer() {
        return lastAnswer;
    }

    /**
     * Appends the computed answer to the existing history line.
     * Intended to be called after a new input is started following a result.
     *
     * @param answer the answer value to append to the existing history line
     */
    public void showAnswerInHistory(final String answer) {
        history = history + " " + answer;
    }
}
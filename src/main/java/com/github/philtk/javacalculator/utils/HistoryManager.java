package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.token.AnswerToken;
import com.github.philtk.javacalculator.model.Value;

public class HistoryManager {
    private String history = "";
    private AnswerToken lastAnswer = new AnswerToken();

    public void saveHistory(final String text) {
        history = text + " =";
    }

    public String getHistory() {
        return history;
    }

    public void saveAnswer(final Value answer) {
        lastAnswer = new AnswerToken(answer);
    }

    public AnswerToken getLastAnswer() {
        return lastAnswer;
    }

}
package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.Calculator;
import com.github.philtk.javacalculator.exceptions.CalculatorErrorType;
import com.github.philtk.javacalculator.exceptions.CalculatorException;
import com.github.philtk.javacalculator.model.token.Token;
import com.github.philtk.javacalculator.model.token.TokenFactory;
import com.github.philtk.javacalculator.model.token.TokenManager;
import com.github.philtk.javacalculator.model.Value;

import java.util.List;

public class ProcessManager {
    private final TokenManager tokenManager;
    private final ContextManager contextManager;
    private final HistoryManager historyManager;

    public ProcessManager(final TokenManager tokenManager, final ContextManager contextManager, final HistoryManager historyManager) {
        this.tokenManager = tokenManager;
        this.contextManager = contextManager;
        this.historyManager = historyManager;
    }

    public void processInput(final InputType inputType, final EventType eventType) {
        final InputType adaptedInputType = contextManager.getUsableInputType(inputType, tokenManager.getTokens(), eventType);
        if(contextManager.shouldClearOnNextInput()) {
            tokenManager.clear();
            contextManager.setResultDisplayed(false);
        }
        switch (adaptedInputType.getCategory()) {
            case CONTROL -> handleControlInput(adaptedInputType);
            case OPERATOR -> handleOperatorInput(adaptedInputType);
            case NUMBER -> handleNumberInput(adaptedInputType);
            case ANSWER -> handleAnswerInput();
            default -> throw new IllegalArgumentException("Unbekannte Kategorie: " + adaptedInputType.getCategory());
        }
    }

    private void handleControlInput(final InputType inputType) {
        switch (inputType) {
            case CLEAR -> tokenManager.clear();
            case BACKSPACE -> tokenManager.removeLast();
            case EQUAL -> calculate();
            default -> throw new IllegalArgumentException("Unerwarteter Steuerbefehl: " + inputType);
        }
    }

    private void handleOperatorInput(final InputType inputType) {
        if(tokenManager.getTokens().isEmpty()) {
            tokenManager.addToken(historyManager.getLastAnswer());
        }
        tokenManager.addToken(TokenFactory.createToken(inputType));
    }

    private void handleNumberInput(final InputType inputType) {
        tokenManager.addNumber(inputType);
    }

    private void handleAnswerInput() {
        tokenManager.addToken(historyManager.getLastAnswer());
    }

    private void calculate() {
        try {
            historyManager.saveHistory(tokenManager.getDisplayText());
            final Value result = calculateResult();
            applyCalculationResult(result);
            historyManager.saveAnswer(result);
        } catch (CalculatorException e) {
            tokenManager.clear();
            tokenManager.addToken(TokenFactory.createToken(CalculatorErrorType.fromException(e)));
        }
        contextManager.setResultDisplayed(true);
    }

    private Value calculateResult() {
        final List<Token> tokens = tokenManager.getTokens();
        return Calculator.calculate(tokens);
    }

    private void applyCalculationResult(final Value result) {
        tokenManager.clear();
        tokenManager.addToken(result.getNumberToken());
    }
}

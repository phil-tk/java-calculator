package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.Calculator;
import com.github.philtk.javacalculator.exceptions.CalculatorErrorType;
import com.github.philtk.javacalculator.exceptions.CalculatorException;
import com.github.philtk.javacalculator.model.token.Token;
import com.github.philtk.javacalculator.model.token.TokenFactory;
import com.github.philtk.javacalculator.model.token.TokenManager;
import com.github.philtk.javacalculator.model.Value;

import java.util.List;

/**
 * Manages the processing of user input and interactions with the calculator's core logic.
 * Delegates input handling based on its category and manages calculations and history storage.
 *
 * @author Phil Winkel
 */
public class ProcessManager {
    private final TokenManager tokenManager;
    private final ContextManager contextManager;
    private final HistoryManager historyManager;

    /**
     * Constructs a ProcessManager instance with the necessary dependencies.
     *
     * @param tokenManager   manages the tokens representing user input
     * @param contextManager manages the calculator's state and context
     * @param historyManager manages calculation history and last result storage
     */
    public ProcessManager(final TokenManager tokenManager, final ContextManager contextManager, final HistoryManager historyManager) {
        this.tokenManager = tokenManager;
        this.contextManager = contextManager;
        this.historyManager = historyManager;
    }

    /**
     * Processes a given input type and performs the appropriate action.
     *
     * @param inputType the type of input received
     * @param eventType the event associated with the input
     * @throws IllegalArgumentException if an unknown category is encountered
     */
    public void processInput(final InputType inputType, final EventType eventType) {
        final InputType adaptedInputType = contextManager.getUsableInputType(inputType, tokenManager.getTokens(), eventType);
        prepareForNewInput();
        switch (adaptedInputType.getCategory()) {
            case CONTROL -> handleControlInput(adaptedInputType);
            case OPERATOR -> handleOperatorInput(adaptedInputType);
            case NUMBER -> handleNumberInput(adaptedInputType);
            case ANSWER -> handleAnswerInput();
            default -> throw new IllegalArgumentException("Unknown category: " + adaptedInputType.getCategory());
        }
    }

    /**
     * Clears the input if the last result was just displayed.
     * Before clearing, appends the last result to the history display.
     */
    private void prepareForNewInput() {
        if (contextManager.shouldClearOnNextInput()) {
            historyManager.showAnswerInHistory(tokenManager.getDisplayText());
            tokenManager.clear();
            contextManager.setResultDisplayed(false);
        }
    }

    /**
     * Handles control input actions such as clear, backspace, and equal.
     *
     * @param inputType the control input type
     * @throws IllegalArgumentException if an unexpected control command is received
     */
    private void handleControlInput(final InputType inputType) {
        switch (inputType) {
            case CLEAR -> tokenManager.clear();
            case BACKSPACE -> handleBackspace();
            case EQUAL -> calculate();
            default -> throw new IllegalArgumentException("Unexpected control command: " + inputType);
        }
    }

    /**
     * Handles operator input by adding an operator token.
     * If the input is the first in an expression, the last answer is used.
     *
     * @param inputType the operator input type
     */
    private void handleOperatorInput(final InputType inputType) {
        if (tokenManager.getTokens().isEmpty()) {
            tokenManager.addToken(historyManager.getLastAnswer());
        }
        tokenManager.addToken(TokenFactory.createToken(inputType));
    }

    /**
     * Handles numerical input by adding the number token.
     *
     * @param inputType the numerical input type
     */
    private void handleNumberInput(final InputType inputType) {
        tokenManager.addNumber(inputType);
    }

    /**
     * Handles answer input by inserting the last calculated result into the token sequence.
     */
    private void handleAnswerInput() {
        tokenManager.addToken(historyManager.getLastAnswer());
    }

    /**
     * Performs the calculation based on the current token list.
     * Handles exceptions by displaying an error token.
     */
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

    /**
     * Safely removes the last token if the input is not empty.
     */
    private void handleBackspace() {
        if (!tokenManager.getTokens().isEmpty()) {
            tokenManager.removeLast();
        }
    }

    /**
     * Computes the calculation result based on the current token list.
     *
     * @return the computed result as a Value object
     */
    private Value calculateResult() {
        final List<Token> tokens = tokenManager.getTokens();
        return Calculator.calculate(tokens);
    }

    /**
     * Applies the calculated result by clearing the token manager and storing the result.
     *
     * @param result the calculated result
     */
    private void applyCalculationResult(final Value result) {
        tokenManager.clear();
        tokenManager.addToken(result.getNumberToken());
    }
}

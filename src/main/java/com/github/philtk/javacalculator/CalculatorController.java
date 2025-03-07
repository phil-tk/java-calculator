package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.ui.DisplayManager;
import com.github.philtk.javacalculator.utils.EventType;
import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.utils.ProcessManager;

/**
 * The CalculatorController handles user input and updates the display.
 * It coordinates the processManager and displayManager to process inputs and update the UI accordingly.
 *
 * @author Phil Winkel
 */
public class CalculatorController {
    private final ProcessManager processManager;
    private final DisplayManager displayManager;

    /**
     * Constructs a new CalculatorController with the provided ProcessManager and DisplayManager.
     *
     * @param processManager the process manager responsible for handling input processing
     * @param displayManager the display manager responsible for updating the UI
     */
    public CalculatorController(final ProcessManager processManager, final DisplayManager displayManager) {
        this.processManager = processManager;
        this.displayManager = displayManager;
    }

    /**
     * Processes the input based on its type and associated event type, then updates the display.
     *
     * @param inputType the type of input (e.g., number, operator)
     * @param eventType the type of event (e.g., button press)
     * @throws IllegalArgumentException if the input type or event type is invalid
     */
    public void processInput(final InputType inputType, final EventType eventType) {
        if (inputType == null || eventType == null) {
            throw new IllegalArgumentException("InputType and EventType cannot be null.");
        }
        processManager.processInput(inputType, eventType);
        displayManager.update();
    }
}

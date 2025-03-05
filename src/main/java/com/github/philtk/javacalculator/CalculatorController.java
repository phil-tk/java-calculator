package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.ui.DisplayManager;
import com.github.philtk.javacalculator.utils.EventType;
import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.utils.ProcessManager;

public class CalculatorController {
    private final ProcessManager processManager;
    private final DisplayManager displayManager;

    public CalculatorController(final ProcessManager processManager, final DisplayManager displayManager) {
        this.processManager = processManager;
        this.displayManager = displayManager;
    }

    public void processInput(final InputType inputType, final EventType eventType) {
        processManager.processInput(inputType, eventType);
        displayManager.update();
    }
}

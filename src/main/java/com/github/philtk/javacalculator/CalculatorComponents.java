package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.ui.DisplayManager;
import com.github.philtk.javacalculator.utils.ProcessManager;

/**
 * This record holds the main components of the Calculator:
 * - processManager: Responsible for processing the calculator input and operations.
 * - displayManager: Responsible for managing the display and updating the UI.
 * - controller: Coordinates the interaction between the processManager and displayManager.
 *
 * @author Phil Winkel
 */
public record CalculatorComponents(ProcessManager processManager, DisplayManager displayManager,
                                   CalculatorController controller) {
}

package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.ui.DisplayManager;
import com.github.philtk.javacalculator.utils.ProcessManager;

public record CalculatorComponents(ProcessManager processManager, DisplayManager displayManager,
                                   CalculatorController controller) {
}

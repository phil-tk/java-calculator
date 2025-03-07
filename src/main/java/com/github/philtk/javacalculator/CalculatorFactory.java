package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.model.token.TokenManager;
import com.github.philtk.javacalculator.ui.DisplayManager;
import com.github.philtk.javacalculator.utils.ContextManager;
import com.github.philtk.javacalculator.utils.HistoryManager;
import com.github.philtk.javacalculator.utils.ProcessManager;

/**
 * Factory class for creating the components needed for the Calculator application.
 * This class encapsulates the instantiation logic of the calculator components.
 *
 * @author Phil Winkel
 */
public class CalculatorFactory {

    /**
     * Creates the main components required for the Calculator.
     *
     * @return a CalculatorComponents object containing all necessary components
     */
    public static CalculatorComponents createCalculatorComponents() {
        final TokenManager tokenManager = new TokenManager();
        final ContextManager contextManager = new ContextManager();
        final HistoryManager historyManager = new HistoryManager();

        final ProcessManager processManager = new ProcessManager(tokenManager, contextManager, historyManager);
        final DisplayManager displayManager = new DisplayManager(tokenManager, historyManager);
        final CalculatorController controller = new CalculatorController(processManager, displayManager);

        return new CalculatorComponents(processManager, displayManager, controller);
    }
}

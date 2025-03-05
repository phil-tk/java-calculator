package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.model.token.TokenManager;
import com.github.philtk.javacalculator.ui.DisplayManager;
import com.github.philtk.javacalculator.utils.ContextManager;
import com.github.philtk.javacalculator.utils.HistoryManager;
import com.github.philtk.javacalculator.utils.ProcessManager;

public class CalculatorFactory {
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

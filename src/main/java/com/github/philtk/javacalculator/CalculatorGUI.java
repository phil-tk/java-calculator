package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.ui.CalculatorView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The GUI class for the Calculator application.
 * This class is responsible for launching and displaying the calculator UI.
 *
 * @author Phil Winkel
 */
public class CalculatorGUI extends Application {
    private static CalculatorComponents components;

    /**
     * Starts the Calculator GUI application.
     * Initializes the necessary components and displays the UI.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(final Stage primaryStage) {
        if (components == null) {
            components = CalculatorFactory.createCalculatorComponents();
        }

        CalculatorView view = new CalculatorView(components.displayManager(), components.controller());
        view.start(primaryStage);
    }
}

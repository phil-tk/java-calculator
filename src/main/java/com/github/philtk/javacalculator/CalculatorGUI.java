package com.github.philtk.javacalculator;

import com.github.philtk.javacalculator.ui.CalculatorView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CalculatorGUI extends Application {
    private static CalculatorComponents components;

    @Override
    public void start(final Stage primaryStage) {
        if (components == null) {
            components = CalculatorFactory.createCalculatorComponents();
        }

        CalculatorView view = new CalculatorView(components.displayManager(), components.controller());
        view.start(primaryStage);
    }
}


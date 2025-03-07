package com.github.philtk.javacalculator;

import javafx.application.Application;

/**
 * Main entry point for launching the Calculator application.
 * This class is responsible for starting the CalculatorGUI application.
 *
 * @author Phil Winkel
 */
public class CalculatorApp {

    /**
     * The main method launches the Calculator GUI.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        Application.launch(CalculatorGUI.class, args);
    }
}

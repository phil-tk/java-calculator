package com.github.philtk.javacalculator.input;

import com.github.philtk.javacalculator.CalculatorController;
import com.github.philtk.javacalculator.utils.EventType;
import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.utils.InputTypeUtil;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import java.util.List;
import java.util.Optional;

/**
 * Handles user input for the calculator, both from button clicks and keyboard inputs.
 */
public class CalculatorInputHandler {
    private final CalculatorController controller;

    /**
     * Constructs an input handler for the calculator.
     *
     * @param scene      The JavaFX scene to attach key event handlers.
     * @param buttonList The list of calculator buttons.
     * @param controller The controller responsible for processing input.
     * @throws IllegalArgumentException if any parameter is null.
     */
    public CalculatorInputHandler(final Scene scene, final List<Button> buttonList, final CalculatorController controller) {
        if (scene == null || buttonList == null || controller == null) {
            throw new IllegalArgumentException("Scene, button list, and controller must not be null.");
        }
        this.controller = controller;
        addEventHandlers(scene, buttonList);
    }

    /**
     * Adds event handlers for button clicks and key presses.
     *
     * @param scene      The JavaFX scene to attach key event handlers.
     * @param buttonList The list of buttons to attach action listeners.
     */
    private void addEventHandlers(final Scene scene, final List<Button> buttonList) {
        buttonList.forEach(button -> button.setOnAction(this::handleButtonPress));
        scene.setOnKeyPressed(this::handleKeyPress);
    }

    /**
     * Handles a button press event and processes the corresponding input.
     *
     * @param event The action event triggered by a button press.
     * @throws IllegalArgumentException if the button's user data is not of type InputType.
     */
    private void handleButtonPress(final ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            Object userData = button.getUserData();
            if (!(userData instanceof InputType inputType)) {
                throw new IllegalArgumentException("Button user data must be of type InputType.");
            }
            controller.processInput(inputType, EventType.BUTTON);
            button.getScene().getRoot().requestFocus();
        } else {
            throw new UnsupportedOperationException("Unknown event source in button press handler.");
        }
    }

    /**
     * Handles a key press event and processes the corresponding input if valid.
     *
     * @param event The key event triggered by a keyboard input.
     */
    private void handleKeyPress(final KeyEvent event) {
        Optional<InputType> optionalInputType = InputTypeUtil.getInputTypeFrom(event.getCode().getCode());
        optionalInputType.ifPresent(inputType -> controller.processInput(inputType, EventType.KEY));
    }
}

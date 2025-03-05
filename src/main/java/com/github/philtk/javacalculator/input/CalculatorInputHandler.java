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

public class CalculatorInputHandler {
    private final CalculatorController controller;

    public CalculatorInputHandler(final Scene scene, final List<Button> buttonList, final CalculatorController controller) {
        this.controller = controller;
        addEventHandlers(scene, buttonList);
    }

    private void addEventHandlers(final Scene scene, final List<Button> buttonList) {
        buttonList.forEach(button -> {
            button.setOnAction(this::handleButtonPress);
        });

        scene.setOnKeyPressed(this::handleKeyPress);
    }


    private void handleButtonPress(final ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            InputType inputType = (InputType) button.getUserData();
            controller.processInput(inputType, EventType.BUTTON);
            button.getScene().getRoot().requestFocus();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private void handleKeyPress(final KeyEvent event) {
        Optional<InputType> optionalInputType = InputTypeUtil.getInputTypeFrom(event.getCode().getCode());
        if (optionalInputType.isEmpty()) {
            return;
        }
        controller.processInput(optionalInputType.get(), EventType.KEY);
    }
}
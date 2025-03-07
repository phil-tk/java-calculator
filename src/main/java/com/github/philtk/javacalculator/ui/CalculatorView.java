package com.github.philtk.javacalculator.ui;

import com.github.philtk.javacalculator.input.CalculatorButton;
import com.github.philtk.javacalculator.CalculatorController;
import com.github.philtk.javacalculator.input.CalculatorInputHandler;
import com.github.philtk.javacalculator.styles.AppColor;
import com.github.philtk.javacalculator.styles.AppFont;
import com.github.philtk.javacalculator.styles.ButtonStyle;
import com.github.philtk.javacalculator.utils.InputType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the graphical user interface (GUI) of the calculator application.
 * This class is responsible for managing the layout, input fields, buttons, and binding the display data.
 * It does not handle user input events directly; instead, it relies on external handlers for event processing.
 *
 * @author Phil Winkel
 */
public class CalculatorView {
    private final DisplayManager displayManager;
    private final CalculatorController controller;


    /**
     * Constructs a CalculatorView instance.
     *
     * @param displayManager The manager handling display updates.
     * @param controller     The controller managing calculator logic.
     */
    public CalculatorView(final DisplayManager displayManager, final CalculatorController controller) {
        this.displayManager = displayManager;
        this.controller = controller;
    }

    /**
     * Initializes and starts the calculator UI.
     *
     * @param primaryStage The main application stage.
     */
    public void start(final Stage primaryStage) {
        final TextField historyField = createTextField(AppColor.TEXT_SECONDARY, AppFont.HISTORY_AREA, 60);
        final TextField inputField = createTextField(AppColor.TEXT_PRIMARY, AppFont.INPUT_AREA, 100);

        final GridPane grid = createButtonGrid();
        final List<Button> buttonList = initializeButtons(grid);

        final VBox root = new VBox(5, historyField, inputField, grid);
        root.setPadding(new Insets(5));
        root.setStyle("-fx-background-color: " + toRgbString(AppColor.BACKGROUND));
        root.setAlignment(Pos.CENTER);
        VBox.setVgrow(grid, Priority.ALWAYS);

        final Scene scene = new Scene(root, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
        scene.getRoot().requestFocus();

        inputField.textProperty().bind(displayManager.inputTextProperty());
        historyField.textProperty().bind(displayManager.historyTextProperty());

        new CalculatorInputHandler(scene, buttonList, controller);
    }

    /**
     * Creates a non-editable text field with specified styling.
     *
     * @param textColor The text color.
     * @param font      The font style.
     * @param height    The height of the text field.
     * @return The configured TextField instance.
     */
    private TextField createTextField(final AppColor textColor, final AppFont font, final int height) {
        TextField textField = new TextField();
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: " + toRgbString(AppColor.BACKGROUND) +
                "; -fx-text-fill: " + toRgbString(textColor) +
                "; -fx-font-size: " + (int) font.getFont().getSize() + "px;");
        textField.setFont(font.getFont());
        textField.setPrefHeight(height);
        return textField;
    }

    /**
     * Converts an AppColor to an RGB string representation.
     *
     * @param appColor The AppColor instance.
     * @return The RGB string representation.
     */
    private String toRgbString(final AppColor appColor) {
        if (appColor == null) {
            throw new IllegalArgumentException("AppColor cannot be null");
        }
        Color color = appColor.getColor();
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    /**
     * Creates a button grid layout with predefined constraints.
     *
     * @return The configured GridPane instance.
     */
    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(2));

        for (int i = 0; i < 4; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(25);
            col.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(col);
        }
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(20);
            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }
        return grid;
    }

    /**
     * Initializes calculator buttons and adds them to the button grid.
     *
     * @param grid The GridPane where buttons are placed.
     * @return A list of created button instances.
     */
    private List<Button> initializeButtons(final GridPane grid) {
        if (grid == null) {
            throw new IllegalArgumentException("GridPane cannot be null");
        }

        InputType[][] buttons = {
                {InputType.ANSWER, InputType.CLEAR, InputType.BACKSPACE, InputType.DIVIDE},
                {InputType.SEVEN, InputType.EIGHT, InputType.NINE, InputType.MULTIPLY},
                {InputType.FOUR, InputType.FIVE, InputType.SIX, InputType.SUBTRACT},
                {InputType.ONE, InputType.TWO, InputType.THREE, InputType.ADD},
                {InputType.SIGN, InputType.ZERO, InputType.DECIMAL, InputType.EQUAL}
        };

        List<Button> buttonList = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                InputType inputType = buttons[i][j];
                ButtonStyle buttonStyle = InputStyleMapper.getStyleFor(inputType);
                if (buttonStyle == null) {
                    throw new IllegalStateException("Button style mapping not found for: " + inputType);
                }
                CalculatorButton button = new CalculatorButton(buttonStyle, inputType);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                buttonList.add(button);
                grid.add(button, j, i);
            }
        }
        return buttonList;
    }
}

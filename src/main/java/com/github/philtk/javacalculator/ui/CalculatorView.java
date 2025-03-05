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

public class CalculatorView {
    private final DisplayManager displayManager;
    private final CalculatorController controller;

    public CalculatorView(final DisplayManager displayManager, final CalculatorController controller) {
        this.displayManager = displayManager;
        this.controller = controller;
    }

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

    private String toRgbString(final AppColor appColor) {
        Color color = appColor.getColor();
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

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

    private List<Button> initializeButtons(final GridPane grid) {
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
                CalculatorButton button = new CalculatorButton(buttonStyle, inputType);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                buttonList.add(button);
                grid.add(button, j, i);
            }
        }
        return buttonList;
    }
}

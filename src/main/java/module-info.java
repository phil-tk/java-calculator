module com.github.philtk.javacalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.github.philtk.javacalculator to javafx.fxml;
    exports com.github.philtk.javacalculator;
    exports com.github.philtk.javacalculator.model;
    opens com.github.philtk.javacalculator.model to javafx.fxml;
    exports com.github.philtk.javacalculator.ui;
    opens com.github.philtk.javacalculator.ui to javafx.fxml;
    exports com.github.philtk.javacalculator.input;
    opens com.github.philtk.javacalculator.input to javafx.fxml;
    exports com.github.philtk.javacalculator.exceptions;
    opens com.github.philtk.javacalculator.exceptions to javafx.fxml;
    exports com.github.philtk.javacalculator.utils;
    opens com.github.philtk.javacalculator.utils to javafx.fxml;
    exports com.github.philtk.javacalculator.model.token;
    opens com.github.philtk.javacalculator.model.token to javafx.fxml;
    exports com.github.philtk.javacalculator.model.expression;
    opens com.github.philtk.javacalculator.model.expression to javafx.fxml;
    exports com.github.philtk.javacalculator.styles;
    opens com.github.philtk.javacalculator.styles to javafx.fxml;
}
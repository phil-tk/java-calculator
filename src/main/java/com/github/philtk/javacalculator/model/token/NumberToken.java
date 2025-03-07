package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.model.Value;
import com.github.philtk.javacalculator.utils.Category;
import com.github.philtk.javacalculator.utils.InputType;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a numerical token that can store and process numbers entered by the user.
 *
 * @author Phil Winkel
 */
public class NumberToken extends DynamicToken {
    private final ArrayList<InputType> number;
    private static final Category CATEGORY = Category.NUMBER;

    /**
     * Constructs a NumberToken with the initial input.
     *
     * @param firstInputType the first digit or sign
     */
    public NumberToken(final InputType firstInputType) {
        number = new ArrayList<>();
        number.add(firstInputType);
    }

    @Override
    public Category getCategory() {
        return CATEGORY;
    }

    @Override
    public boolean isValid() {
        boolean hasDigit = false;
        boolean hasDecimal = false;

        for (InputType current : number) {
            if (current.isDigit()) {
                hasDigit = true;
            }
            if (current == InputType.DECIMAL) {
                if (hasDecimal) {
                    return false; // Multiple decimal points are invalid
                }
                hasDecimal = true;
            }
            if (current == InputType.SIGN) {
                if (hasDigit || hasDecimal) {
                    return false; // Sign must be at the beginning
                }
            }
        }
        return hasDigit;
    }

    @Override
    public String getDisplayText() {
        return number.stream()
                .map(InputType::getDisplayTxt)
                .collect(Collectors.joining());
    }

    @Override
    public void add(final InputType inputType) {
        number.add(inputType);
    }

    @Override
    public boolean canRemoveLast() {
        return number.size() > 1;
    }

    @Override
    public void removeLast() {
        if (number.isEmpty()) {
            throw new IllegalStateException("Cannot remove from an empty NumberToken");
        }
        number.removeLast();
    }

    /**
     * Converts the token into a numerical value.
     *
     * @return the numerical representation of the token
     * @throws UnsupportedOperationException if the token is not valid
     */
    public Value getValue() {
        if (!isValid()) {
            throw new UnsupportedOperationException("Cannot retrieve value from an invalid NumberToken");
        }
        final String num = getSign() + number.stream()
                .filter(inputType -> inputType != InputType.SIGN)
                .map(InputType::getInternalText)
                .collect(Collectors.joining());
        return new Value(num);
    }

    private String getSign() {
        final boolean negativeSign = number.stream()
                .filter(inputType -> inputType == InputType.SIGN)
                .count() % 2 == 1;
        return negativeSign ? InputType.SIGN.getInternalText() : "";
    }

    /**
     * Determines if a sign can still be added to this number.
     *
     * @return true if a sign can be added, false otherwise
     */
    public boolean allowsSign() {
        return number.stream().noneMatch(inputType -> inputType.isDigit() || inputType == InputType.DECIMAL);
    }
}
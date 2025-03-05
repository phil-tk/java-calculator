package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.model.Value;
import com.github.philtk.javacalculator.utils.Category;
import com.github.philtk.javacalculator.utils.InputType;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NumberToken extends DynamicToken {
    private final ArrayList<InputType> number;
    private static final Category CATEGORY = Category.NUMBER;

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
                    return false;
                }
                hasDecimal = true;
            }

            if (current == InputType.SIGN) {
                if (hasDigit || hasDecimal) {
                    return false;
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
        number.removeLast();
    }

    public Value getValue() {
        if (!isValid()) {
            throw new UnsupportedOperationException("Not possible to do " + this + ".getValue()");
        }
        final String num = getSign() + number.stream().filter(inputType -> inputType != InputType.SIGN).map(InputType::getInternalText).collect(Collectors.joining());
        return new Value(num);
    }

    private String getSign() {
        final boolean negativeSign = number.stream().filter(inputType -> inputType == InputType.SIGN).count() % 2 == 1;
        if(negativeSign) {
            return InputType.SIGN.getInternalText();
        } return "";
    }

    public boolean allowsSign(){
        return number.stream().noneMatch(inputType -> inputType.isDigit() || inputType == InputType.DECIMAL);
    }

}

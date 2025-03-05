package com.github.philtk.javacalculator.utils;

import java.util.Arrays;
import java.util.Optional;

public class InputTypeUtil {

    private InputTypeUtil() {

    }

    public static Optional<InputType> getInputTypeFrom(final int keyCode) {
        return Arrays.stream(InputType.values())
                .filter(type -> type.getKeyCodes().contains(keyCode))
                .findFirst();
    }

    public static InputType fromInternalText(final String text) {
        return Arrays.stream(InputType.values())
                .filter(type -> text.equals(type.getInternalText()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Kein passender InputType für: " + text));
    }
}

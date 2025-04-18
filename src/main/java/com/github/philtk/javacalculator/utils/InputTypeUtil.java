package com.github.philtk.javacalculator.utils;

import java.util.Arrays;
import java.util.Optional;

/**
 * Utility class for handling InputType mappings and lookups.
 * Provides methods to determine the corresponding InputType from a given key code or text representation.
 *
 * @author Phil Winkel
 */
public class InputTypeUtil {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private InputTypeUtil() {
    }

    /**
     * Retrieves the corresponding InputType based on the provided key code and Shift key state.
     *
     * @param keyCode    The key code to look up.
     * @param isShiftDown Whether the Shift key is pressed.
     * @return An Optional containing the matching InputType, or empty if no match is found.
     */
    public static Optional<InputType> getInputTypeFrom(final int keyCode, final boolean isShiftDown) {
        if (isShiftDown) {
            return Arrays.stream(InputType.values())
                    .filter(type -> type.getKeyCodesShift().contains(keyCode))
                    .findFirst();
        }
        return Arrays.stream(InputType.values())
                .filter(type -> type.getKeyCodes().contains(keyCode))
                .findFirst();
    }

    /**
     * Retrieves the corresponding InputType based on its internal text representation.
     *
     * @param text the internal text of the InputType
     * @return the matching InputType
     * @throws UnsupportedOperationException if no matching InputType is found
     */
    public static InputType fromInternalText(final String text) {
        return Arrays.stream(InputType.values())
                .filter(type -> text.equals(type.getInternalText()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("No matching InputType for: " + text));
    }
}
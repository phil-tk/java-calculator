package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.token.Token;

import java.util.List;

public class SyntaxErrorChecker {

    public static boolean isError(final List<Token> tokens) {
        return hasLexicalError(tokens) || hasSyntaxError(tokens);
    }

    private static boolean hasLexicalError(final List<Token> tokens) {
        return tokens.stream().anyMatch(token -> !token.isValid());
    }

    private static boolean hasSyntaxError(final List<Token> tokens) {
        if (tokens.isEmpty()) {
            return false;
        }

        if (!tokens.stream().allMatch(SyntaxErrorChecker::isAllowedCategory)) {
            return true;
        }

        if (isOperator(tokens.getFirst()) || isOperator(tokens.getLast())) {
            return true;
        }

        return hasConsecutiveOperators(tokens);
    }

    private static boolean isAllowedCategory(Token token) {
        return switch (token.getCategory()) {
            case ANSWER, NUMBER, OPERATOR -> true;
            default -> false;
        };
    }

    private static boolean isOperator(Token token) {
        return token.getCategory() == Category.OPERATOR;
    }

    private static boolean hasConsecutiveOperators(List<Token> tokens) {
        Category previousCategory = null;
        for (Token token : tokens) {
            if (previousCategory == Category.OPERATOR && token.getCategory() == Category.OPERATOR) {
                return true;
            }
            previousCategory = token.getCategory();
        }
        return false;
    }
}

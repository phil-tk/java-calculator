package com.github.philtk.javacalculator.utils;

import com.github.philtk.javacalculator.model.token.Token;

import java.util.List;

/**
 * Utility class for checking syntax errors in a list of tokens.
 *
 * @author Phil Winkel
 */
public class SyntaxErrorChecker {

    /**
     * Checks if there is any error (lexical or syntax) in the given token list.
     *
     * @param tokens the list of tokens to check
     * @return true if an error is found, false otherwise
     */
    public static boolean isError(final List<Token> tokens) {
        return hasLexicalError(tokens) || hasSyntaxError(tokens);
    }

    /**
     * Checks if the token list contains a lexical error.
     *
     * @param tokens the list of tokens to check
     * @return true if a lexical error is found, false otherwise
     */
    private static boolean hasLexicalError(final List<Token> tokens) {
        return tokens.stream().anyMatch(token -> !token.isValid());
    }

    /**
     * Checks if the token list contains a syntax error.
     *
     * @param tokens the list of tokens to check
     * @return true if a syntax error is found, false otherwise
     */
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

    /**
     * Checks if a token belongs to an allowed category.
     *
     * @param token the token to check
     * @return true if the token is in an allowed category, false otherwise
     */
    private static boolean isAllowedCategory(Token token) {
        return switch (token.getCategory()) {
            case ANSWER, NUMBER, OPERATOR -> true;
            default -> false;
        };
    }

    /**
     * Checks if a token is an operator.
     *
     * @param token the token to check
     * @return true if the token is an operator, false otherwise
     */
    private static boolean isOperator(Token token) {
        return token.getCategory() == Category.OPERATOR;
    }

    /**
     * Checks if there are consecutive operators in the token list.
     *
     * @param tokens the list of tokens to check
     * @return true if consecutive operators are found, false otherwise
     */
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
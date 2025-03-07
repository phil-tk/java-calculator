package com.github.philtk.javacalculator.model.expression;

import com.github.philtk.javacalculator.model.token.*;
import com.github.philtk.javacalculator.utils.SyntaxErrorChecker;
import com.github.philtk.javacalculator.exceptions.SyntaxErrorException;
import com.github.philtk.javacalculator.utils.InputType;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses a list of tokens into an expression tree.
 * Handles operator precedence and ensures correct syntax.
 *
 * @author Phil Winkel
 */
public class ExpressionParser {

    /**
     * Parses a list of tokens into an {@link Expression}.
     *
     * @param tokens The list of tokens to parse.
     * @return The parsed expression tree.
     * @throws SyntaxErrorException If the syntax is invalid.
     */
    public static Expression parseExpression(final List<Token> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Token list must not be null or empty.");
        }

        final List<Token> copiedTokens = new ArrayList<>(tokens);
        insertMultiplicationBetweenNumbers(copiedTokens);

        if (SyntaxErrorChecker.isError(copiedTokens)) {
            throw new SyntaxErrorException();
        }

        final List<Expression> expressions = createExpressions(copiedTokens);
        final List<InputType> operators = extractOperators(copiedTokens);

        handleMultiplicationAndDivisionFirst(expressions, operators);
        handleAdditionAndSubtraction(expressions, operators);

        return expressions.getFirst();
    }

    /**
     * Inserts implicit multiplication operators between consecutive numbers.
     *
     * @param copiedTokens The list of tokens to modify.
     */
    private static void insertMultiplicationBetweenNumbers(final List<Token> copiedTokens) {
        for (int i = 0; i < copiedTokens.size() - 1; i++) {
            if ((copiedTokens.get(i) instanceof NumberToken || copiedTokens.get(i) instanceof AnswerToken) &&
                    (copiedTokens.get(i + 1) instanceof NumberToken || copiedTokens.get(i + 1) instanceof AnswerToken)) {
                copiedTokens.add(i + 1, TokenFactory.createToken(InputType.MULTIPLY));
            }
        }
    }

    /**
     * Creates expressions from tokens.
     *
     * @param copiedTokens The list of tokens.
     * @return A list of expressions.
     */
    private static List<Expression> createExpressions(final List<Token> copiedTokens) {
        List<Expression> expressions = new ArrayList<>();
        for (Token token : copiedTokens) {
            if (token instanceof NumberToken) {
                expressions.add(new NumberExpression(((NumberToken) token).getValue()));
            } else if (token instanceof AnswerToken) {
                expressions.add(new NumberExpression(((AnswerToken) token).getValue()));
            }
        }
        return expressions;
    }

    /**
     * Extracts operators from tokens.
     *
     * @param copiedTokens The list of tokens.
     * @return A list of operators.
     */
    private static List<InputType> extractOperators(final List<Token> copiedTokens) {
        List<InputType> operators = new ArrayList<>();
        for (Token token : copiedTokens) {
            if (token instanceof OperatorToken) {
                operators.add(((OperatorToken) token).getOperator());
            }
        }
        return operators;
    }

    /**
     * Handles multiplication and division before addition and subtraction.
     *
     * @param expressions The list of expressions.
     * @param operators   The list of operators.
     */
    private static void handleMultiplicationAndDivisionFirst(final List<Expression> expressions, final List<InputType> operators) {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == InputType.MULTIPLY || operators.get(i) == InputType.DIVIDE) {
                if (i + 1 >= expressions.size()) {
                    throw new IllegalStateException("Invalid expression structure detected.");
                }

                Expression left = expressions.get(i);
                Expression right = expressions.get(i + 1);
                InputType operator = operators.get(i);

                expressions.set(i, new BinaryExpression(left, right, operator));
                expressions.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    /**
     * Handles addition and subtraction.
     *
     * @param expressions The list of expressions.
     * @param operators   The list of operators.
     */
    private static void handleAdditionAndSubtraction(final List<Expression> expressions, final List<InputType> operators) {
        while (!operators.isEmpty()) {
            if (expressions.size() < 2) {
                throw new IllegalStateException("Invalid expression structure detected.");
            }

            Expression left = expressions.removeFirst();
            Expression right = expressions.removeFirst();
            InputType operator = operators.removeFirst();

            expressions.addFirst(new BinaryExpression(left, right, operator));
        }
    }
}
package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.InputType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a list of tokens and provides methods to manipulate them.
 * Tokens are added, removed, and displayed in a sequence.
 *
 * @author Phil Winkel
 */
public class TokenManager {
    private final List<Token> tokens;

    /**
     * Constructs a TokenManager with an empty list of tokens.
     */
    public TokenManager() {
        this.tokens = new ArrayList<>();
    }

    /**
     * Adds a number token to the list. If the last token is a number, it adds to that token.
     *
     * @param inputType the InputType of the number to be added
     */
    public void addNumber(final InputType inputType) {
        if (tokens.isEmpty() || !(tokens.get(tokens.size() - 1) instanceof NumberToken numberToken)) {
            tokens.add(TokenFactory.createToken(inputType));
        } else {
            numberToken.add(inputType);
        }
    }

    /**
     * Clears all tokens from the list.
     */
    public void clear() {
        tokens.clear();
    }

    /**
     * Removes the last token from the list. If it's a dynamic token, attempts to remove the last element from it.
     *
     * @throws IndexOutOfBoundsException if the token list is empty
     */
    public void removeLast() {
        if (!tokens.isEmpty()) {
            Token lastToken = tokens.get(tokens.size() - 1);
            if (lastToken instanceof DynamicToken dynamicToken && dynamicToken.canRemoveLast()) {
                dynamicToken.removeLast();
            } else {
                tokens.remove(tokens.size() - 1);
            }
        } else {
            throw new IndexOutOfBoundsException("Cannot remove from an empty token list.");
        }
    }

    /**
     * Adds a token to the list of tokens.
     *
     * @param token the Token to be added
     */
    public void addToken(final Token token) {
        tokens.add(token);
    }

    /**
     * Gets the display text of all tokens in the list, concatenated as a string.
     *
     * @return a string representation of all tokens
     */
    public String getDisplayText() {
        return tokens.stream()
                .map(Token::getDisplayText)
                .collect(Collectors.joining());
    }

    /**
     * Returns a copy of the token list to ensure immutability.
     *
     * @return an unmodifiable list of tokens
     */
    public List<Token> getTokens() {
        return List.copyOf(tokens);
    }
    
    /**
     * Checks whether the current token list is empty.
     *
     * @return true if there are no tokens, false otherwise
     */
    public boolean hasNoTokens(){
        return tokens.isEmpty();
    }
}


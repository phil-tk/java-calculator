package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.utils.InputType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenManager {
    private final List<Token> tokens;;

    public TokenManager() {
        this.tokens = new ArrayList<>();
    }

    public void addNumber(final InputType inputType) {
        if (tokens.isEmpty() || !(tokens.getLast() instanceof NumberToken numberToken)) {
            tokens.add(TokenFactory.createToken(inputType));
        } else {
            numberToken.add(inputType);
        }
    }

    public void clear() {
        tokens.clear();
    }

    public void removeLast() {
        if (!tokens.isEmpty()) {
            Token lastToken = tokens.getLast();
            if (lastToken instanceof DynamicToken dynamicToken && dynamicToken.canRemoveLast()) {
                dynamicToken.removeLast();
            } else {
                tokens.removeLast();
            }
        }
    }

    public void addToken(final Token token) {
        tokens.add(token);
    }

    public String getDisplayText() {
        return tokens.stream()
                .map(Token::getDisplayText)
                .collect(Collectors.joining());
    }

    public List<Token> getTokens() {
        return List.copyOf(tokens);
    }
}

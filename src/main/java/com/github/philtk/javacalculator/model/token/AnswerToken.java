package com.github.philtk.javacalculator.model.token;

import com.github.philtk.javacalculator.model.Value;
import com.github.philtk.javacalculator.utils.Category;
import com.github.philtk.javacalculator.utils.InputType;

public class AnswerToken extends StaticToken{
    private final NumberToken numberToken;
    private final Value value;

    public AnswerToken(final Value answer){
        this.numberToken = answer.getNumberToken();
        this.value = answer;
    }

    public AnswerToken(){
        this.numberToken = new NumberToken(InputType.ZERO);
        value = numberToken.getValue();
    }

    @Override
    public Category getCategory() {
        return Category.ANSWER;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getDisplayText() {
        return InputType.ANSWER.getDisplayTxt();
    }

    public Value getValue(){
        return value;
    }
}

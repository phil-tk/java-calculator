package com.github.philtk.javacalculator.model;

import com.github.philtk.javacalculator.model.token.NumberToken;
import com.github.philtk.javacalculator.utils.InputType;
import com.github.philtk.javacalculator.utils.InputTypeUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class Value{
    private static final int INTERNAL_PRECISION = 20;
    private static final int EXTERNAL_PRECISION = 10;

    private final BigDecimal bigDecimal;
    private static final MathContext innerContext = new MathContext(INTERNAL_PRECISION, RoundingMode.HALF_UP);
    private static final MathContext exteriorContext = new MathContext(EXTERNAL_PRECISION, RoundingMode.HALF_UP);

    public Value(final String value) {
        bigDecimal = new BigDecimal(value).round(innerContext);
    }
    private Value(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Value add(final Value value) {
        return new Value(bigDecimal.add(value.bigDecimal, innerContext));
    }
    public Value subtract(final Value value) {
        return new Value(bigDecimal.subtract(value.bigDecimal, innerContext));
    }
    public Value multiply(final Value value) {
        return new Value(bigDecimal.multiply(value.bigDecimal, innerContext));
    }
    public Value divide(final Value value) {
        if (value.bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            /*TODO*/
        }
        return new Value(bigDecimal.divide(value.bigDecimal, INTERNAL_PRECISION, RoundingMode.HALF_UP));
    }

    public boolean equals(final Value value){
        return bigDecimal.compareTo(value.bigDecimal) == 0;
    }


    public NumberToken getNumberToken() {
        final BigDecimal roundedBigDecimal = bigDecimal.round(exteriorContext).stripTrailingZeros();

        final List<InputType> inputTypes = roundedBigDecimal.toPlainString()
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(InputTypeUtil::fromInternalText)
                .toList();

        final NumberToken numberToken = new NumberToken(inputTypes.getFirst());
        inputTypes.stream().skip(1).forEach(numberToken::add);

        return numberToken;
    }

}

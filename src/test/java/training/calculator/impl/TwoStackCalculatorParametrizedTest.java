package training.calculator.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import training.calculator.util.CalculatorUtil;


@DisplayName("Parametrized Test suits for TwoStack calculator")
class TwoStackCalculatorParametrizedTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @ParameterizedTest
    @MethodSource("sourceData")
    void calculate(String arg1, String arg2, String operation, double result) {
        double v = calculator.calculate(String.format("(%s)(%s)%s", arg1, arg2, operation));
        assertEquals(result, v);
    }

    static Stream<Arguments> sourceData() {
        return Stream.of(
            Arguments.of("1", "2", "+", 3.0),
            Arguments.of("1", "2", "-", -1.0),
            Arguments.of("1", "2", "*", 2.0),
            Arguments.of("10", "2", "/", 5.0)
        );
    }


}
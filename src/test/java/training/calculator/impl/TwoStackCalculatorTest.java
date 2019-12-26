package training.calculator.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import training.calculator.util.CalculatorUtil;

/**
 * JUnit 5 docs
 * https://junit.org/junit5/docs/current/user-guide/
 */

@DisplayName("Regular Test suits for TwoStack calculator")
class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);


    @Nested
    @DisplayName("Failing Scenarios")
    class FailingScenarios {

        @DisplayName("NULL as input parameter")
        @Test
        void whenNullForInputParameterThenIllegalArgumentExceptionThrown() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculator.calculate(null);
            });
            assertEquals("Invalid operation: n", exception.getMessage());
        }

        @DisplayName("One of input parameter is NULL")
        @Test
        void whenSecondParameterIsNullThenIllegalArgumentExceptionThrown() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculator.calculate("(1)()+");
            });
            assertEquals("Invalid expression provided", exception.getMessage());
        }

        @DisplayName("Division by zero")
        @Test
        void whenSecondParameterIsZeroForDivideThenArithmeticExceptionThrown() {
            Exception exception = assertThrows(ArithmeticException.class, () -> {
                calculator.calculate("(10)(0)/");
            });
            assertEquals("/ by zero", exception.getMessage());
        }

        @DisplayName("Unknown operation")
        @Test
        void whenOperationInvalidThenIllegalArgumentExceptionThrown() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculator.calculate("(10)(0)^");
            });

            assertEquals("Invalid operation: ^", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Successful scenarios")
    class SunnyDayScenarios {

        @DisplayName("PLUS")
        @Test
        void canCalculatePlus() {
            double v = calculator.calculate("(1)(2)+");
            assertEquals(3.0, v);
        }

        @DisplayName("MINUS")
        @Test
        void canCalculateMinus() {
            double v = calculator.calculate("(1)(2)-");
            assertEquals(-1.0, v);
        }


        @DisplayName("MULTIPLY")
        @Test
        void canCalculateMultiply() {
            double v = calculator.calculate("(1)(2)*");
            assertEquals(2, v);
        }

        @DisplayName("DIVIDE")
        @Test
        void canCalculateDivide() {
            double v = calculator.calculate("(10)(5)/");
            assertEquals(2.0, v);
        }

    }

}
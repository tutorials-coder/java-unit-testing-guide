package com.lambdatest.tutorial;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterizedCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, 20, 30",
            "5, 5, 10"
    })
    void testAddition(int a, int b, int expected) {
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.add(a, b));
    }

    @Test
    void testException() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 1 / 0;
        });
    }

}

package com.lambdatest.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Calculator Tests")
class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test addition with zero")
    void testAddWithZero() {
        assertEquals(5, calculator.add(5, 0));
        assertEquals(5, calculator.add(0, 5));
        assertEquals(0, calculator.add(0, 0));
    }

    @Test
    @DisplayName("Test addition with negative numbers")
    void testAddWithNegativeNumbers() {
        assertEquals(-8, calculator.add(-5, -3));
        assertEquals(2, calculator.add(5, -3));
        assertEquals(-2, calculator.add(-5, 3));
    }

    @Test
    @DisplayName("Test subtraction with zero")
    void testSubtractWithZero() {
        assertEquals(5, calculator.subtract(5, 0));
        assertEquals(-5, calculator.subtract(0, 5));
        assertEquals(0, calculator.subtract(0, 0));
    }

    @Test
    @DisplayName("Test subtraction with negative numbers")
    void testSubtractWithNegativeNumbers() {
        assertEquals(-2, calculator.subtract(-5, -3));
        assertEquals(8, calculator.subtract(5, -3));
        assertEquals(-8, calculator.subtract(-5, 3));
    }

    @Test
    @DisplayName("Test multiplication with zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(5, 0));
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(0, calculator.multiply(0, 0));
    }

    @Test
    @DisplayName("Test multiplication with negative numbers")
    void testMultiplyWithNegativeNumbers() {
        assertEquals(15, calculator.multiply(-5, -3));
        assertEquals(-15, calculator.multiply(5, -3));
        assertEquals(-15, calculator.multiply(-5, 3));
    }

    @Test
    @DisplayName("Test division by zero")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0));
    }

    @Test
    @DisplayName("Test division with zero numerator")
    void testDivideWithZeroNumerator() {
        assertEquals(0.0, calculator.divide(0, 5));
    }

    @Test
    @DisplayName("Test division with negative numbers")
    void testDivideWithNegativeNumbers() {
        assertEquals(1.6666666666666667, calculator.divide(-5, -3));
        assertEquals(-1.6666666666666667, calculator.divide(5, -3));
        assertEquals(-1.6666666666666667, calculator.divide(-5, 3));
    }

    @ParameterizedTest(name = "Test addition: {0} + {1} = {2}")
    @CsvSource({
        "1, 1, 2",
        "2, 3, 5",
        "10, 20, 30",
        "-1, -1, -2",
        "0, 0, 0"
    })
    @DisplayName("Parameterized addition tests")
    void testParameterizedAddition(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name = "Test subtraction: {0} - {1} = {2}")
    @CsvSource({
        "1, 1, 0",
        "5, 3, 2",
        "20, 10, 10",
        "-1, -1, 0",
        "0, 0, 0"
    })
    @DisplayName("Parameterized subtraction tests")
    void testParameterizedSubtraction(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest(name = "Test multiplication: {0} * {1} = {2}")
    @CsvSource({
        "1, 1, 1",
        "2, 3, 6",
        "10, 20, 200",
        "-1, -1, 1",
        "0, 0, 0"
    })
    @DisplayName("Parameterized multiplication tests")
    void testParameterizedMultiplication(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }

    @ParameterizedTest(name = "Test division: {0} / {1} = {2}")
    @CsvSource({
        "1, 1, 1.0",
        "6, 2, 3.0",
        "10, 2, 5.0",
        "-6, -2, 3.0",
        "0, 5, 0.0"
    })
    @DisplayName("Parameterized division tests")
    void testParameterizedDivision(int a, int b, double expected) {
        assertEquals(expected, calculator.divide(a, b));
    }
} 
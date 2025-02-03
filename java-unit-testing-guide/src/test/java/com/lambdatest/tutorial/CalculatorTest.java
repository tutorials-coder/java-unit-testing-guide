package com.lambdatest.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    void testAddition() {
        assertEquals(4, calculator.add(2, 2));
    }
    
    @Test
    void testSubtraction() {
        assertEquals(2, calculator.subtract(4, 2));
    }
    
    @Test
    void testMultiplication() {
        assertEquals(6, calculator.multiply(2, 3));
    }
    
    @Test
    void testDivision() {
        assertEquals(2.0, calculator.divide(4, 2));
    }
    
    @Test
    void testDivisionByZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.divide(4, 0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
} 
package com.example.kata.calculator;

import com.example.kata.calculator.exception.NegativeNumberException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    @Test
    void shouldReturnZeroForEmptyString() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(0, calculator.add(""));
    }

    @Test
    void testSingleNumberReturnsValue() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(1, calculator.add("1"));
        assertEquals(5, calculator.add("5"));
    }

    @Test
    void shouldReturnSumForTwoNumbers() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(3, calculator.add("1,2"));
        assertEquals(7, calculator.add("3,4"));
    }

    @Test
    void shouldReturnSumUnknownAmountOfNumbers() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    void shouldReturnumForNewLineAsDelimiter() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(10, calculator.add("1\n2\n3,4"));
    }

    @Test
    void shouldReturnSumForCustomSingleCharacterDelimiter() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(10, calculator.add("//|\n3|7"));
    }

    @Test
    void shouldReturnSumCustomMultiCharacterDelimiter() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(6, calculator.add("//[***]\n1***2***3"));
        assertEquals(10, calculator.add("//[###]\n2###3###5"));
    }

    @Test
    void testMultipleDelimiters() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        assertEquals(10, calculator.add("//[!][#]\n4!3#3"));
    }

    @Test
    void testMultipleDelimitersWithDifferentLengths() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(6, calculator.add("//[***][%%%]\n1***2%%%3"));
        assertEquals(15, calculator.add("//[abc][def]\n4abc5def6"));
    }

    @Test
    void testNegativeNumbersThrowException() {
        StringCalculator calculator = new StringCalculator();

        Exception exception = assertThrows(NegativeNumberException.class, () -> calculator.add("-1,2,-3"));
        assertEquals("Negatives not allowed: [-1, -3]", exception.getMessage());
    }

    @Test
    void testNumbersGreaterThan1000AreIgnored() throws Exception {
        StringCalculator calculator = new StringCalculator();

        assertEquals(2, calculator.add("2,1001"));
        assertEquals(1002, calculator.add("2,1000"));
    }

    @Test
    void testInvalidNumberFormatThrowException() throws NumberFormatException {
        StringCalculator calculator = new StringCalculator();

        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.add("1,\\n"));
        assertEquals("Invalid number format", exception.getMessage());
    }

}
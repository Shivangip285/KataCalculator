package com.example.kata.calculator.exception;

import java.util.List;

public class NegativeNumberException extends Exception {
    private final List<Integer> negativeNumbers;

    public NegativeNumberException(List<Integer> negativeNumbers) {
        super("Negatives not allowed: " + negativeNumbers);
        this.negativeNumbers = negativeNumbers;
    }

    public List<Integer> getNegativeNumbers() {
        return negativeNumbers;
    }
}


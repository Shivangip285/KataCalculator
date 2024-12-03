package com.example.kata.calculator.delimeter;

public class DefaultDelimiterStrategy implements DelimiterStrategy {
    @Override
    public String getDelimiter() {
        return ",|\n"; // Default delimiter is comma or newline
    }
}

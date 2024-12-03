package com.example.kata.calculator.delimeter;

import java.util.regex.Pattern;

public class SingleDelimiterStrategy implements DelimiterStrategy {
    private final String delimiter;

    public SingleDelimiterStrategy(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public String getDelimiter() {
        return Pattern.quote(delimiter);
    }
}

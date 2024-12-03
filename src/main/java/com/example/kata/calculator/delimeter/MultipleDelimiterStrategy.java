package com.example.kata.calculator.delimeter;

import java.util.regex.Pattern;

public class MultipleDelimiterStrategy implements DelimiterStrategy {
    private final String delimiterSpec;

    public MultipleDelimiterStrategy(String delimiterSpec) {
        this.delimiterSpec = delimiterSpec;
    }

    @Override
    public String getDelimiter() {
        StringBuilder regexBuilder = new StringBuilder();
        for (String delim : delimiterSpec.split("\\]\\[")) {
            regexBuilder.append(Pattern.quote(delim.replace("[", "").replace("]", ""))).append("|");
        }
        return regexBuilder.substring(0, regexBuilder.length() - 1);
    }
}

package com.example.kata.calculator;

import com.example.kata.calculator.delimeter.DefaultDelimiterStrategy;
import com.example.kata.calculator.delimeter.DelimiterStrategy;
import com.example.kata.calculator.delimeter.MultipleDelimiterStrategy;
import com.example.kata.calculator.delimeter.SingleDelimiterStrategy;
import com.example.kata.calculator.exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;


public class StringCalculator {
    private DelimiterStrategy delimiterStrategy;
    public int minLimit=0;
    public int maxLimit=1000;
    public StringCalculator() {
        this.delimiterStrategy = new DefaultDelimiterStrategy();
    }
    public int add(String numbers) throws NegativeNumberException,NumberFormatException {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] tokens = getTokens(numbers);
        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String token : tokens) {
            try{
            int parsedNum = Integer.parseInt(token);
            if (parsedNum < minLimit) {
                negatives.add(parsedNum);
            } else if (parsedNum <= maxLimit) {
                sum += parsedNum;
            }}
            catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid number format");
            }
        }

        if (!negatives.isEmpty()) {
            throw new NegativeNumberException(negatives);
        }

        return sum;
    }

    private String[] getTokens(String numbers) {
        if (numbers.startsWith("//")) {
            delimiterStrategy = selectDelimiterStrategy(numbers);
            numbers = numbers.substring(numbers.indexOf("\n") + 1);
        }
        return numbers.split(delimiterStrategy.getDelimiter());
    }

    private DelimiterStrategy selectDelimiterStrategy(String numbers) {
        String delimiterSpec = numbers.substring(2, numbers.indexOf("\n"));
        if (delimiterSpec.startsWith("[")) {
            return new MultipleDelimiterStrategy(delimiterSpec);
        } else {
            return new SingleDelimiterStrategy(delimiterSpec);
        }
    }

}
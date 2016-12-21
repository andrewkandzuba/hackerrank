package org.hackerrank.java.interview.pl.logic;

import java.util.function.IntFunction;

/**
 * Functional mapper @see {@link org.hackerrank.java.interview.pl.logic.FizzBuzzBang#getFizzBuzzBang(Integer)} for implementation's details
 */
public class FizzBuzzBangFunction implements IntFunction<String> {
    @Override
    public String apply(int value) {
        return FizzBuzzBang.getFizzBuzzBang(value);
    }
}

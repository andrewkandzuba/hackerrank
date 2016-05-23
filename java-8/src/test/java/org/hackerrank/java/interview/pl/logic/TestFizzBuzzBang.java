package org.hackerrank.java.interview.pl.logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class TestFizzBuzzBang {
    @Test
    public void testFunctionalCases() throws Exception {
        // Full house test
        Assert.assertEquals("FizzBuzzBang", FizzBuzzBang.getFizzBuzzBang(0));
        Assert.assertEquals("FizzBuzzBang", FizzBuzzBang.getFizzBuzzBang(3*5*7));

        // "No bang" test
        Assert.assertEquals("Splat", FizzBuzzBang.getFizzBuzzBang(1));

        // One person's bang tests
        Assert.assertEquals("Fizz", FizzBuzzBang.getFizzBuzzBang(3));
        Assert.assertEquals("Buzz", FizzBuzzBang.getFizzBuzzBang(5));
        Assert.assertEquals("Bang", FizzBuzzBang.getFizzBuzzBang(7));

        // Partially assambled gang
        Assert.assertEquals("FizzBuzz", FizzBuzzBang.getFizzBuzzBang(3*5));
        Assert.assertEquals("FizzBang", FizzBuzzBang.getFizzBuzzBang(3*7));
        Assert.assertEquals("BuzzBang", FizzBuzzBang.getFizzBuzzBang(5*7));

    }

    @Test
    public void testFunctionalMapper() throws Exception {
        FizzBuzzBangFunction mapper = new FizzBuzzBangFunction();
        IntStream.rangeClosed(0, 100).mapToObj(mapper).forEach(System.out::println);
    }
}

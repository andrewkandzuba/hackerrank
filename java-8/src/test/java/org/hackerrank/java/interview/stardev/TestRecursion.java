package org.hackerrank.java.interview.stardev;


import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.Recursion.*;

public class TestRecursion {

    @Test
    public void testFactorial() throws Exception {
        Assert.assertTrue(factorial(1) == 1);
        Assert.assertTrue(factorial(2) == 2);
        Assert.assertTrue(factorial(3) == 6);
        Assert.assertTrue(factorial(4) == 24);
    }

    @Test
    public void testBunnyEars() throws Exception {
        Assert.assertTrue(bunnyEars(0) == 0);
        Assert.assertTrue(bunnyEars(1) == 2);
        Assert.assertTrue(bunnyEars(2) == 4);
        Assert.assertTrue(bunnyEars(3) == 6);
    }

    @Test
    public void testFibonacci() throws Exception {
        Assert.assertTrue(fibonacci(0) == 0);
        Assert.assertTrue(fibonacci(1) == 1);
        Assert.assertTrue(fibonacci(2) == 1);
        Assert.assertTrue(fibonacci(3) == 2);
        Assert.assertTrue(fibonacci(4) == 3);
        Assert.assertTrue(fibonacci(5) == 5);
        Assert.assertTrue(fibonacci(6) == 8);
        Assert.assertTrue(fibonacci(7) == 13);
    }

    @Test
    public void testBunnyEars2() throws Exception {
        Assert.assertTrue(bunnyEars2(0) == 0);
        Assert.assertTrue(bunnyEars2(1) == 2);
        Assert.assertTrue(bunnyEars2(2) == 5);
        Assert.assertTrue(bunnyEars2(3) == 7);
        Assert.assertTrue(bunnyEars2(4) == 10);
    }

    @Test
    public void testTriangle() throws Exception {
        Assert.assertTrue(triangle(0) == 0);
        Assert.assertTrue(triangle(1) == 1);
        Assert.assertTrue(triangle(2) == 3);
        Assert.assertTrue(triangle(3) == 6);
        Assert.assertTrue(triangle(4) == 10);
    }

    @Test
    public void testSumDigits() throws Exception {
        Assert.assertTrue(sumDigits(126) == 9);
        Assert.assertTrue(sumDigits(49) == 13);
        Assert.assertTrue(sumDigits(12) == 3);
    }


    @Test
    public void testCount7() throws Exception {
        Assert.assertTrue(count7(717) == 2);
        Assert.assertTrue(count7(7) == 1);
        Assert.assertTrue(count7(123) == 0);
        Assert.assertTrue(count7(70070) == 2);
    }
}

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

    @Test
    public void testCount8() throws Exception {
        Assert.assertTrue(count8(8) == 1);
        Assert.assertTrue(count8(818) == 2);
        Assert.assertTrue(count8(8818) == 4);
        Assert.assertTrue(count8(80080) == 2);
        Assert.assertTrue(count8(88081) == 4);
        Assert.assertTrue(count8(8888) == 7);
    }

    @Test
    public void testPowerN() throws Exception {
        Assert.assertTrue(powerN(3, 1) == 3);
        Assert.assertTrue(powerN(3, 2) == 9);
        Assert.assertTrue(powerN(3, 3) == 27);
        Assert.assertTrue(powerN(4, 4) == 256);
        Assert.assertTrue(powerN(0, 10) == 0);
        Assert.assertTrue(powerN(10, 0) == 1);
    }

    @Test
    public void testCountX() throws Exception {
        Assert.assertTrue(countX("xxhixx") == 4);
        Assert.assertTrue(countX("xhixhix") == 3);
        Assert.assertTrue(countX("XXHIXX") == 0);
        Assert.assertTrue(countX("hi") == 0);
    }

    @Test
    public void testCountHi() throws Exception {
        Assert.assertTrue(countHi("xxhixx") == 1);
        Assert.assertTrue(countHi("xhixhix") == 2);
        Assert.assertTrue(countHi("XXHIXX") == 0);
        Assert.assertTrue(countHi("hi") == 1);
        Assert.assertTrue(countHi("Hi") == 0);
        Assert.assertTrue(countHi("hI") == 0);
        Assert.assertTrue(countHi("HI") == 0);
        Assert.assertTrue(countHi("hihi") == 2);
        Assert.assertTrue(countHi("xhihi") == 2);
        Assert.assertTrue(countHi("HIhihi") == 2);
    }

    @Test
    public void testChangeXY() throws Exception {
        Assert.assertTrue(changeXY("codex").equals("codey"));
        Assert.assertTrue(changeXY("xxhixx").equals("yyhiyy"));
        Assert.assertTrue(changeXY("XXHIXX").equals("XXHIXX"));
        Assert.assertTrue(changeXY("xhixhix").equals("yhiyhiy"));
    }

    @Test
    public void testStrDist() throws Exception {
        Assert.assertTrue(strDist("catcowcat", "cat") == 9);
        Assert.assertTrue(strDist("catcowcat", "cow") == 3);
        Assert.assertTrue(strDist("cccatcowcatxx", "cat") == 9);

        Assert.assertTrue(strDist("catcowcatxxxcatsdfsdfsdfsdffsdcat", "cat") == 21);

        Assert.assertTrue(strDist("xxcatxx", "cat") == 0);
        Assert.assertTrue(strDist("catxx", "cat") == 0);
        Assert.assertTrue(strDist("catxx", "cat") == 0);
        Assert.assertTrue(strDist("xxxxxxx", "cat") == 0);

        Assert.assertTrue(strDist("abccatcowcatcatxyz", "cat") == 12);
    }
}

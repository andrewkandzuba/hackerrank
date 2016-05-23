package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hackerrank.java.interview.stardev.Arrays1.*;

public class TestArrays1 {


    @Test
    public void testRotateLeft3() throws Exception {
        Assert.assertTrue(Arrays.equals(rotateLeft3(new int[]{1, 2, 3}), new int[]{2, 3, 1}));
        Assert.assertTrue(Arrays.equals(rotateLeft3(new int[]{5, 11, 9}), new int[]{11, 9, 5}));
        Assert.assertTrue(Arrays.equals(rotateLeft3(new int[]{7, 0, 0}), new int[]{0, 0, 7}));
    }
}

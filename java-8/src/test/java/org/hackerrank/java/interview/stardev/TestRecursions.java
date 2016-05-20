package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.Recursion2.*;

public class TestRecursions {

    @Test
    public void testBacktrackingRecursionProblem() throws Exception {
        Assert.assertTrue(groupSum(0, new int[]{2, 4, 8}, 10));
        Assert.assertTrue(groupSum(0, new int[]{2, 4, 8}, 14));
        Assert.assertFalse(groupSum(0, new int[]{2, 4, 8}, 9));
        Assert.assertTrue(groupSum(0, new int[]{2, 8, 11, 1, 2, 10, 1, 8, 6}, 10));
        Assert.assertTrue(groupSum(0, new int[]{5, 8, 11, 1, 2, 17, 1, 27, 6}, 10));
    }

    @Test
    public void testBacktrackingRecursionProblemSum6() throws Exception {
        Assert.assertTrue(groupSum6(0, new int[]{5, 6, 2}, 8));
        Assert.assertFalse(groupSum6(0, new int[]{5, 6, 2}, 9));
        Assert.assertFalse(groupSum6(0, new int[]{5, 6, 2}, 7));
        Assert.assertFalse(groupSum6(0, new int[]{5, 2, 4, 6}, 9));
        Assert.assertTrue(groupSum6(0, new int[]{}, 0));

        Assert.assertTrue(groupSum6(0, new int[]{2, 4, 8}, 10));
        Assert.assertTrue(groupSum6(0, new int[]{2, 4, 8}, 14));
        Assert.assertFalse(groupSum6(0, new int[]{2, 4, 8}, 9));

        Assert.assertFalse(groupSum6(0, new int[]{2, 0, 7, 0, 6, 0, 0, 1, 6}, 8));
        Assert.assertTrue(groupSum6(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6}, 12));
        Assert.assertFalse(groupSum6(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6}, 6));


        Assert.assertTrue(groupSum6(0, new int[]{5, 6, 2}, 8));
        Assert.assertFalse(groupSum6(0, new int[]{5, 6, 2}, 9));
        Assert.assertFalse(groupSum6(0, new int[]{5, 6, 2}, 7));

        Assert.assertTrue(groupSum6(0, new int[]{1}, 1));
        Assert.assertFalse(groupSum6(0, new int[]{9}, 1));
        Assert.assertTrue(groupSum6(0, new int[]{}, 0));

        Assert.assertTrue(groupSum6(0, new int[]{3, 2, 4, 6}, 8));
        Assert.assertTrue(groupSum6(0, new int[]{6, 2, 4, 3}, 8));
        Assert.assertFalse(groupSum6(0, new int[]{5, 2, 4, 6}, 9));
        Assert.assertFalse(groupSum6(0, new int[]{6, 2, 4, 5}, 9));
        Assert.assertFalse(groupSum6(0, new int[]{3, 2, 4, 6}, 3));
        Assert.assertTrue(groupSum6(0, new int[]{1, 6, 2, 6, 4}, 12));
        Assert.assertTrue(groupSum6(0, new int[]{1, 6, 2, 6, 4}, 13));
        Assert.assertFalse(groupSum6(0, new int[]{1, 6, 2, 6, 4}, 4));
        Assert.assertFalse(groupSum6(0, new int[]{1, 6, 2, 6, 4}, 9));
        Assert.assertTrue(groupSum6(0, new int[]{1, 6, 2, 6, 5}, 14));
        Assert.assertTrue(groupSum6(0, new int[]{1, 6, 2, 6, 5}, 15));
        Assert.assertFalse(groupSum6(0, new int[]{1, 6, 2, 6, 5}, 16));
    }

    @Test
    public void testSum6() throws Exception {
        Assert.assertEquals(6, sumOf(0, new int[]{5, 6, 2}, 6));
        Assert.assertEquals(0, sumOf(0, new int[]{2, 4, 8}, 6));
        Assert.assertEquals(12, sumOf(0, new int[]{2, 0, 7, 0, 6, 0, 0, 1, 6}, 6));
        Assert.assertEquals(12, sumOf(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6}, 6));
        Assert.assertEquals(18, sumOf(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6, 12, 12, 0, 1, 6, 9}, 6));
    }

    @Test
    public void testGroupNoAdj() throws Exception {
        Assert.assertTrue(groupNoAdj(0, new int[]{2, 5, 10, 4}, 12));
        Assert.assertFalse(groupNoAdj(0, new int[]{2, 5, 10, 4}, 14));
        Assert.assertFalse(groupNoAdj(0, new int[]{2, 5, 10, 4}, 7));
        Assert.assertTrue(groupNoAdj(0, new int[]{2, 4, 5, 10, 4}, 14));

        Assert.assertTrue(groupNoAdj(0, new int[]{}, 0));
        Assert.assertTrue(groupNoAdj(0, new int[]{1}, 1));
        Assert.assertTrue(groupNoAdj(0, new int[]{2, 1, 0}, 2));
        Assert.assertTrue(groupNoAdj(0, new int[]{1}, 0));
    }

    @Test
    public void testSum5() throws Exception {
        Assert.assertTrue(groupSum5(0, new int[]{2, 5, 10, 4}, 19));
        Assert.assertTrue(groupSum5(0, new int[]{2, 5, 10, 4}, 17));
        Assert.assertFalse(groupSum5(0, new int[]{2, 5, 10, 4}, 12));
        Assert.assertTrue(groupSum5(0, new int[]{22, 5, 1, 10, 23, 1, 34}, 16));
        Assert.assertFalse(groupSum5(0, new int[]{22, 5, 1, 10, 23, 23, 34}, 16));
        Assert.assertTrue(groupSum5(0, new int[]{22, 5, 23, 1, 10, 23, 1, 34}, 16));
        Assert.assertFalse(groupSum5(0, new int[]{3, 5, 1}, 9));
    }


    @Test
    public void testSumClump() throws Exception {
        Assert.assertTrue(groupSumClump(0, new int[]{2, 4, 8}, 10));
        Assert.assertTrue(groupSumClump(0, new int[]{1, 2, 4, 8, 1}, 14));
        Assert.assertFalse(groupSumClump(0, new int[]{2, 4, 4, 8}, 14));
        Assert.assertTrue(groupSumClump(0, new int[]{0, 4, 4, 0, 4, 4, 4}, 0));
        Assert.assertTrue(groupSumClump(0, new int[]{4, 4}, 0));
        Assert.assertTrue(groupSumClump(0, new int[]{4, 4}, 8));
    }

    @Test
    public void testSplitArray() throws Exception {
        Assert.assertTrue(splitArray(new int[]{}));
        Assert.assertFalse(splitArray(new int[]{1}));
        Assert.assertTrue(splitArray(new int[]{2, 2}));
        Assert.assertFalse(splitArray(new int[]{2, 3}));
        Assert.assertTrue(splitArray(new int[]{5, 2, 3}));
        Assert.assertTrue(splitArray(new int[]{5, 0, 1, 0, 4}));
        Assert.assertFalse(splitArray(new int[]{5, 1, 1, 0, 4}));
        Assert.assertTrue(splitArray(new int[]{1, 1, 1, 0, 3}));
    }
}

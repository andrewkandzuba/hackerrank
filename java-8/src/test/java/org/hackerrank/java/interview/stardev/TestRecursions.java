package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.Recursion2.groupSum;
import static org.hackerrank.java.interview.stardev.Recursion2.groupSum6;
import static org.hackerrank.java.interview.stardev.Recursion2.sumOf;

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

}

package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

public class TestRecursions {

    @Test
    public void testBacktrackingRecursionProblem() throws Exception {
        Assert.assertTrue(groupSum(0, new int[]{2, 4, 8}, 10));
        Assert.assertTrue(groupSum(0, new int[]{2, 4, 8}, 14));
        Assert.assertFalse(groupSum(0, new int[]{2, 4, 8}, 9));
        Assert.assertTrue(groupSum(0, new int[]{8, 2, 1, 0, 10, 1, 8, 6}, 10));
    }

    private boolean groupSum(int start, int[] nums, int target) {

    }

    private boolean groupSum(int start, int[] nums, int length, int target)

}

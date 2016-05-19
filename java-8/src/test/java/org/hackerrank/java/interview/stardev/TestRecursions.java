package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertFalse(groupSum6(0, new int[]{}, 0));

        Assert.assertFalse(groupSum6(0, new int[]{2, 4, 8}, 10));
        Assert.assertFalse(groupSum6(0, new int[]{2, 4, 8}, 14));
        Assert.assertFalse(groupSum6(0, new int[]{2, 4, 8}, 9));

        Assert.assertFalse(groupSum6(0, new int[]{2, 0, 7, 0, 6, 0, 0, 1, 6}, 8));
        Assert.assertTrue(groupSum6(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6}, 12));
        Assert.assertFalse(groupSum6(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6}, 6));


        Assert.assertTrue(groupSum6(0, new int[]{5, 6, 2}, 8));
        Assert.assertFalse(groupSum6(0, new int[]{5, 6, 2}, 9));
        Assert.assertFalse(groupSum6(0, new int[]{5, 6, 2}, 7));

        Assert.assertFalse(groupSum6(0, new int[]{1}, 1));
        Assert.assertFalse(groupSum6(0, new int[]{9}, 1));
        Assert.assertFalse(groupSum6(0, new int[]{}, 0));

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
        Assert.assertEquals(6, sum6(0, new int[]{5, 6, 2}));
        Assert.assertEquals(0, sum6(0, new int[]{2, 4, 8}));

        Assert.assertEquals(12, sum6(0, new int[]{2, 0, 7, 0, 6, 0, 0, 1, 6}));
        Assert.assertEquals(12, sum6(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6}));
        Assert.assertEquals(18, sum6(0, new int[]{5, 0, 6, 0, 0, 0, 0, 0, 6, 12, 12, 0, 1, 6, 9}));
    }


    public boolean groupSum6(int start, int[] nums, int target) {
        int sum6 = sum6(start, nums);
        return sum6 > 0 && groupSum(start, nums, target - sum6);
    }

    public boolean groupSum(int start, int[] nums, int target) {
        return target == 0
                || start != nums.length
                && (groupSum(start + 1, nums, target - nums[start])
                || groupSum(start + 1, nums, target));
    }

    private int sum6(int start, int[] nums) {
        if (start == nums.length)
            return 0;
        return sum6(start + 1, nums) + ((nums[start] == 6) ? nums[start] : 0);
    }

}

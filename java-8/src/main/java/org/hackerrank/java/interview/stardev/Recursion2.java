package org.hackerrank.java.interview.stardev;

public class Recursion2 {

    public static boolean groupSum6(int start, int[] nums, int target) {
        if (target < 0)
            return false;

        if (start >= nums.length)
            return (target == 0);

        if (nums[start] == 6)
            return (groupSum6(start + 1, nums, target - 6));
        else
            return groupSum6(start + 1, nums, target) ||
                    groupSum6(start + 1, nums, target - nums[start]);
    }

    public static boolean groupSum(int start, int[] nums, int target) {
        return target == 0
                || start != nums.length
                && (groupSum(start + 1, nums, target - nums[start])
                || groupSum(start + 1, nums, target));
    }

    public static int sumOf(int start, int[] nums, int value) {
        if (start == nums.length)
            return 0;
        return sumOf(start + 1, nums, value) + ((nums[start] == value) ? nums[start] : 0);
    }

    public static boolean groupNoAdj(int start, int[] nums, int target) {
        if (target < 0)
            return false;

        if (start >= nums.length) {
            return (target == 0);
        }

        return groupNoAdj(start + 2, nums, target) ||
                groupNoAdj(start + 2, nums, target - nums[start])
                || groupNoAdj(start + 1, nums, target);
    }

    public static boolean groupSum5(int start, int[] nums, int target) {
        if (target < 0)
            return false;

        if (start >= nums.length) {
            return (target == 0);
        }

        if (nums[start] % 5 == 0) {
            if (start + 1 < nums.length && nums[start + 1] == 1) {
                return (groupSum5(start + 2, nums, target - nums[start]));
            } else {
                return (groupSum5(start + 1, nums, target - nums[start]));
            }
        } else {
            return groupSum5(start + 1, nums, target) ||
                    groupSum5(start + 1, nums, target - nums[start]);
        }
    }

    public static boolean groupSumClump(int start, int[] nums, int target) {
        if (target < 0)
            return false;

        if (start >= nums.length) {
            return (target == 0);
        }

        if (start + 1 < nums.length && nums[start + 1] == nums[start]) {
            int extend = 0;
            for(int i = start; i < nums.length; i++) {
                if (nums[i] == nums[start]) {
                    extend++;
                }
            }
            return groupSumClump(start + extend, nums, target) ||
                    groupSumClump(start + extend, nums, target - extend * nums[start]);
        } else {
            return groupSumClump(start + 1, nums, target) ||
                    groupSumClump(start + 1, nums, target - nums[start]);
        }
    }

    public boolean splitArray(int[] nums) {
        return true;
    }
}

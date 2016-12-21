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
            for (int i = start; i < nums.length; i++) {
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


    public static boolean splitArray(int[] nums) {
        int total = 0;
        for (int i : nums) total += i;
        return findEquals(0, nums, 0, total);
    }


    private static boolean findEquals(int start, int[] nums, int target, int total) {
        if (start >= nums.length)
            return (total - 2 * target == 0);

        return findEquals(start + 1, nums, target + nums[start], total) ||
                findEquals(start + 1, nums, target, total);
    }

    public static boolean splitOdd10(int[] nums) {
        int total = 0;
        for (int i : nums) total += i;
        return findOdd10(0, nums, 0, total);
    }

    private static boolean findOdd10(int start, int[] nums, int target, int total) {
        if (start >= nums.length) {
            int remain = (total - target);
            return (target % 10 == 0 && remain % 2 > 0) ||
                    (target % 2 > 0 && remain % 10 == 0);
        }
        return findOdd10(start + 1, nums, target + nums[start], total) ||
                findOdd10(start + 1, nums, target, total);
    }

    public static boolean split53(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total += i;
        }
        return find35(0, nums, 0, 0, 0, total);
    }

    private static boolean find35(int start, int[] nums, int target, int target3, int target5, int total) {
        if (start >= nums.length)
            return (2 * (target + target3) - total) == 0 || (2 * (target + target5) == total);

        if (nums[start] % 5 == 0) {
            return find35(start + 1, nums, target, target3, target5 + nums[start], total);
        }
        if (nums[start] % 3 == 0 && nums[start] % 5 > 0) {
            return find35(start + 1, nums, target, target3 + nums[start], target5, total);
        } else {
            return find35(start + 1, nums, target + nums[start], target3, target5, total) ||
                    find35(start + 1, nums, target, target3, target5, total);
        }
    }
}

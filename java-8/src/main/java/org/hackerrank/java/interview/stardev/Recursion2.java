package org.hackerrank.java.interview.stardev;

public class Recursion2 {

    public static boolean groupSum6(int start, int[] nums, int target) {
        if (target < 0)
            return false;

        if (start >= nums.length)
            return (target == 0);

        if (nums[start] == 6)
            return (groupSum6(start+1, nums, target-6));
        else
            return groupSum6(start+1, nums, target) ||
                    groupSum6(start+1, nums, target- nums[start]);
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


    public static void main(String[] args){
        boolean answer = groupSum6(0, new int[]{2, 4, 8}, 10);
        System.out.println(answer);
    }
}

package org.hackerrank.java.interview.stardev;

import java.util.Stack;

public class Array3 {

    public static int maxSpan(int[] nums) {
        int maxSpan = 0;
        for (int i = 0; i < nums.length; i++) {
            maxSpan = Math.max(maxSpan, maxSpan(i, 0, nums, 0));
        }
        return maxSpan;
    }

    private static int maxSpan(int compare, int start, int[] num, int count) {
        if (start >= num.length) {
            return count;
        }

        if (num[compare] == num[start]) {
            return maxSpan(compare, start + 1, num, start - compare + 1);
        }
        return maxSpan(compare, start + 1, num, count);
    }

    public static int[] fix34(int[] nums) {
        Stack<Integer> threes = new Stack<>();
        Stack<Integer> fours = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 3) {
                threes.push(i);
            } else if (nums[i] == 4) {
                fours.push(i);
            }
        }
        for (Integer i : threes) {
            if (i + 1 < nums.length) {
                nums[fours.pop()] = nums[i + 1];
                nums[i + 1] = 4;
            }
        }
        return nums;
    }

    public static int[] fix45(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 5 && (i - 1 >= 0 && nums[i - 1] != 4 || i == 0)) {
                for (int k = i; k < nums.length; k++) {
                    if (nums[k] == 4 && k + 1 < nums.length && nums[k + 1] != 5) {
                        nums[i] = nums[k + 1];
                        nums[k + 1] = 5;
                        break;
                    }
                }
            } else if (nums[i] == 4 && i + 1 < nums.length && nums[i + 1] != 5) {
                for (int k = i; k < nums.length; k++) {
                    if (nums[k] == 5 && k - 1 >= 0 && nums[k - 1] != 4) {
                        nums[k] = nums[i + 1];
                        nums[i + 1] = 5;
                        break;
                    }
                }
            }
        }
        return nums;
    }


}

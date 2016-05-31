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
        for(int i = 0; i < nums.length; i++){
            if (nums[i] == 3){
                threes.push(i);
            } else if (nums[i] == 4){
                fours.push(i);
            }
        }
        for(Integer i : threes){
            if(i + 1 < nums.length){
                nums[fours.pop()] = nums[i+1];
                nums[i+1] = 4;
            }
        }
        return nums;
    }


}

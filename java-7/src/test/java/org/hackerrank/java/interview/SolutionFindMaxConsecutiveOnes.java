package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

public class SolutionFindMaxConsecutiveOnes {

    @Test
    public void testFindMaxConsecutiveOnes(){
        int i = 300 % 300;
        Assert.assertEquals(4, findMaxConsecutiveOnes(new int[]{0,1,1,1,0,0,1,1,0}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int[] count = new int[2];
        int index = 0;
        int max = 0;
        boolean zero = false;
        for (int num : nums) {
            if (num == 1) {
                count[index]++;
                max = Math.max(max, count[0] + count[1]);
            } else {
                zero = true;
                index = index ^ 1;
                count[index] = 0;
            }
        }
        if (zero) {
            max ++;
        }
        return max;
    }
    
    
}

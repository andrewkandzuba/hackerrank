package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

public class SolutionReverseInteger {

    public static int reverse(int x) {
        int reverse = 0;
        try{
            while(Math.abs(x)>0){
                int reminder = x % 10;
                reverse = reverse * 10 + reminder;
                x /= 10;
            }
        } catch (Exception e){
            reverse = 0;
        }
        return reverse;
    }


    @Test
    public void reverseIntegerTest() {
        Assert.assertEquals(123, reverse(321));
        Assert.assertEquals(-123, reverse(-321));
        Assert.assertEquals(21, reverse(120));
        
        int matrix[][] = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24}};
        int i = 10;
    }
}

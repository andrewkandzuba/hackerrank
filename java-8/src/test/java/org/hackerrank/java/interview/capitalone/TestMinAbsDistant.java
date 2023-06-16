package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestMinAbsDistant {

    public class MinAbsDistant {

        int solution(int[] a) {
            int min = Integer.MAX_VALUE;
            int answer = a[0];
            for (int k : a) {
                int sum = 0;
                for (int i : a) {
                    sum += Math.abs(k - i);
                }
                if (min > sum) {
                    min = sum;
                    answer = k;
                }
            }
            return answer;
        }
    }

    @Test
    public void test1() {
        Assert.assertEquals(4, new MinAbsDistant().solution(new int[]{2,4,7}));
    }
}

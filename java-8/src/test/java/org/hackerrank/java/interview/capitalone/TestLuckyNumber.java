package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestLuckyNumber {

    @Test
    public void name() {
        Assert.assertTrue(new LuckyNumber().solution(1230));
        Assert.assertFalse(new LuckyNumber().solution(1231));
    }

    public class LuckyNumber {

        int max_sum = 0;
        int half_sum = 0;
        int max_level = 0;

        boolean solution(int n) {
            traverse(n, 0);
            return (max_sum - half_sum == half_sum);
        }

        void traverse(int n, int level) {
            if (n == 0) return;
            max_sum += n % 10;
            half_sum = max_sum;
            max_level++;

            traverse(n / 10, level + 1);

            if (level >= max_level / 2) {
                half_sum -= n % 10;
            }
        }
    }
}

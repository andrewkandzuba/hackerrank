package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestSimilarArrays {

    @Test
    public void test1() {
        Assert.assertTrue(new SimilarArrays().solution(new int[]{1, 2, 3}, new int[]{2, 1, 3}));
    }

    public class SimilarArrays  {

        boolean solution(int[] a, int[] b) {
            int diff = 0;
            int[] adiff = new int[2];
            int[] bdiff = new int[2];

            for(int i = 0; i < a.length; i++){
                if(a[i] != b[i]){
                    if (diff >= 2) return false;
                    adiff[diff] = a[i];
                    bdiff[diff] = b[i];
                    diff++;
                }
            }
            Arrays.sort(adiff);
            Arrays.sort(bdiff);

            return adiff[0] == bdiff[0] && adiff[1] == bdiff[1];
        }
    }
}

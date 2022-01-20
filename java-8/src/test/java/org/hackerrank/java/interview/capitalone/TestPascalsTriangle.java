package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestPascalsTriangle {

    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> r = new ArrayList<>();
            r.add(Collections.singletonList(1));
            for(int i = 1; i < numRows; i++){
                r.add(generateFrom(r.get(i - 1)));
            }
            return r;
        }

        private List<Integer> generateFrom(List<Integer> array) {
            List<Integer> result = new ArrayList<>();
            int prev = 0;
            for (Integer integer : array) {
                result.add(prev + integer);
                prev = integer;
            }
            result.add(prev);
            return result;
        }
    }

    @Test
    public void test1() {
         List<List<Integer>> r = new Solution().generate(5);
        Assert.assertEquals(5, r.size());

    }
}

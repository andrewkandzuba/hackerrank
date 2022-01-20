package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSpiralMatrix {

    private static int[] toIntArray(List<Integer> intList) {
        int[] intArray = new int[intList.size()];
        AtomicInteger p = new AtomicInteger();
        intList.forEach(integer -> intArray[p.getAndIncrement()] = integer);
        return intArray;
    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7},
                toIntArray(new Solution().spiralOrder(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}
                })));
    }

    @Test
    public void test2() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4},
                toIntArray(new Solution().spiralOrder(new int[][]{
                        {1, 2, 3, 4},
                })));
    }

    @Test
    public void test3() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4},
                toIntArray(new Solution().spiralOrder(new int[][]{
                        {1}, {2}, {3,}, {4},
                })));
    }

    @Test
    public void test4() {
        Assert.assertArrayEquals(new int[]{1, 2, 4, 3},
                toIntArray(new Solution().spiralOrder(new int[][]{
                        {1, 2}, {3, 4},
                })));
    }

    @Test
    public void test5() {
        Assert.assertArrayEquals(new int[]{1},
                toIntArray(new Solution().spiralOrder(new int[][]{
                        {1},
                })));
    }

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList<>();

            int m = matrix.length;
            int n = matrix[0].length;
            int cs = 0, ce = n - 1;
            int rs = 0, re = m - 1;
            int toVisit = m * n;

            while (toVisit > 0) {
                for (int j = cs; j <= ce && toVisit > 0; j++) {
                    result.add(matrix[rs][j]);
                    toVisit--;
                }
                rs++;
                for (int i = rs; i <= re && toVisit > 0; i++) {
                    result.add(matrix[i][ce]);
                    toVisit--;
                }
                ce--;
                for (int j = ce; j >= cs && toVisit > 0; j--) {
                    result.add(matrix[re][j]);
                    toVisit--;
                }
                re--;
                for (int i = re; i >= rs && toVisit > 0; i--) {
                    result.add(matrix[i][cs]);
                    toVisit--;
                }
                cs++;
            }

            return result;
        }
    }
}

package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestDiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] r = new int[m * n];
        int ri = 0;

        for (int line = 1; line <= n + m - 1; line++) {
            int sc = max(0, line - m);
            int count = min(min(line, (n - sc)), m);

            if (line % 2 == 1) {
                for (int j = 0; j < count; j++) {
                    r[ri++] = mat[min(m, line) - j - 1][sc + j];
                }
            } else {
                for (int j = count - 1; j >= 0; j--) {
                    r[ri++] = mat[min(m, line) - j - 1][sc + j];
                }
            }
        }

        return r;
    }

    private static int min(int a, int b) {
        return (a<b) ? a : b;
    }

    private static int min(int a, int b, int c) {
        return min(min(a, b),c);
    }

    private static int max(int a, int b) {
        return (a<b) ? b : a;
    }

    @Test
    public void testOneElementArray() {
        Assert.assertArrayEquals(new int[]{1}, new TestDiagonalTraverse().findDiagonalOrder(new int[][]{
                {1}
        }));
    }

    @Test
    public void testOneWidthArray() {
        Assert.assertArrayEquals(new int[]{1, 2, 3}, new TestDiagonalTraverse().findDiagonalOrder(new int[][]{
                {1},
                {2},
                {3}
        }));
    }

    @Test
    public void testOneHeightArray() {
        Assert.assertArrayEquals(new int[]{1, 2, 3}, new TestDiagonalTraverse().findDiagonalOrder(new int[][]{
                {1, 2, 3},
        }));
    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5, 4, 6}, new TestDiagonalTraverse().findDiagonalOrder(new int[][]{
                {1, 2},
                {3, 4},
                {5, 6}
        }));
    }

    @Test
    public void test2() {
        Assert.assertArrayEquals(new int[]{1, 2, 4, 7, 5, 3, 6, 8, 9}, new TestDiagonalTraverse().findDiagonalOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));
    }

}


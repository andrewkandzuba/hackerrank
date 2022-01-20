package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestMineSweeper {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[][]{
                {1, 2, 1},
                {2, 1, 1},
                {1, 1, 1}
        }, new MineSweeper().solution(new boolean[][]{
                {true, false, false},
                {false, true, false},
                {false, false, false}
        }));
    }

    public class MineSweeper {
        int[][] solution(boolean[][] matrix) {
            int w = matrix[0].length;
            int h = matrix.length;

            int[][] answer = new int[h][w];

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    answer[j][i] += minesAround(i, j, matrix);
                }
            }

            return answer;
        }

        private int minesAround(int c, int r, boolean[][] matrix) {
            int minesDetected = 0;
            for (int i = c - 1; i <= c + 1; i++) {
                for (int j = r - 1; j <= r + 1; j++) {
                    if (i >= 0 && i < matrix[0].length && j >= 0 && j < matrix.length
                            && (c != i || r != j)
                            && matrix[j][i]) {
                        minesDetected++;
                    }
                }
            }
            return minesDetected;
        }

    }
}

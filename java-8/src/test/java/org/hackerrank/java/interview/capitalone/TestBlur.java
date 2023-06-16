package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestBlur {
    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[][]{
                {5, 4},
                {4, 4}
        }, new Blur().solution(new int[][]{
                {7, 4, 0, 1},
                {5, 6, 2, 2},
                {6, 10, 7, 8},
                {1, 4, 2, 0}
        }));
        Assert.assertArrayEquals(new int[][]{
                {40, 30}
        }, new Blur().solution(new int[][]{
                {36, 0, 18, 9},
                {27, 54, 9, 0},
                {81, 63, 72, 45}
        }));
    }

    public class Blur {
        int[][] solution(int[][] image) {
            int w = (image[0].length - 3) + 1;
            int h = (image.length - 3) + 1;
            int[][] answer = new int[h][w];

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int sum = 0;
                    for (int s = i; s < i + 3; s++) {
                        for (int t = j; t < j + 3; t++) {
                            sum += image[t][s];
                        }
                    }
                    answer[j][i] = sum / 9;
                }
            }

            return answer;
        }
    }
}

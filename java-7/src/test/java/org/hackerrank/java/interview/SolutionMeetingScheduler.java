package org.hackerrank.java.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionMeetingScheduler {

    @Test
    public void test() {
        minAvailableDuration(new int[][]
                        {{216397070, 363167701},
                                {98730764, 158208909}, {441003187, 466254040}, {558239978, 678368334}, {683942980, 717766451}},
                new int[][]{
                        {50490609, 222653186}, {512711631, 670791418}, {730229023, 802410205}, {812553104, 891266775}, {230032010, 399152578}
                },
                456085);

    }

    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int n1 = slots1.length;
        int n2 = slots2.length;

        int i1 = 0;
        int i2 = 0;
        
        while (i1 < n1 && i2 < n2) {
            int[] curr1 = slots1[i1];
            int[] curr2 = slots2[i2];

            int start = Math.max(curr1[0], curr2[0]);
            int end = Math.min(curr1[1], curr2[1]);
            if (end - start >= duration) {
               return Arrays.asList(start, start + duration);
            }
            if (curr1[1] < curr2[1]) {
                i1++;
            } else if (curr2[1] < curr1[1]) {
                i2++;
            } else {
                i1++;
                i2++;
            }
        }

        return new ArrayList<>();

    }

}

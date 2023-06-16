package org.hackerrank.java.interview.zillow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeeklyPrepDayOneTests {

    @Test
    public void testPlusMinus() {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        miniMaxSum(arr);
    }

    @Test
    public void regex() {
        String input = "07:05:45PM";
        String[] parts = input.split(":");
        int hours = Integer.parseInt(parts[0]);;
        boolean isPM = parts[parts.length - 1].startsWith("PM", 2);
        if(isPM) {
            if(hours < 12) {
                hours += 12;
            }
        } else {
            if (hours == 12) {
                hours = 0;
            }
        }

        System.out.format("%02d:%s:%s", hours, parts[1], parts[2].substring(0,2));
    }

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        long metricsSumMin = (long) arr.get(0);
        long metricsSumMax = (long) arr.get(0);

        if(arr.size() > 1){
            Collections.sort(arr);

            metricsSumMin = (long) arr.get(0);
            metricsSumMax = (long) arr.get(arr.size() - 1);

            long metricsSumInTheMiddle = 0L;
            for(int i = 1; i < arr.size() - 1; i++){
                metricsSumInTheMiddle += (long) arr.get(i);
            }

            metricsSumMin += metricsSumInTheMiddle;
            metricsSumMax += metricsSumInTheMiddle;
        }

        System.out.format("%d %d%n", metricsSumMin, metricsSumMax);

    }

    public static void plusMinus(List<Integer> arr) {
        // Write your code here

        int counterPositive = 0;
        int counterZero = 0;
        int counterNegative = 0;

        for(int e : arr) {
            if (e == 0) {
                counterZero++;
            } else if (e <  0) {
                counterNegative++;
            } else {
                counterPositive++;
            }
        }

        int size = arr.size();

        float merticsPositiveRatio = (float) counterPositive / size;
        float merticsZeroRatio = (float) counterZero/ size;
        float merticsNegativeRatio = (float) counterNegative / size;

        System.out.format("%.6f%n", merticsPositiveRatio);
        System.out.format("%.6f%n", merticsZeroRatio);
        System.out.format("%.6f%n", merticsNegativeRatio);
    }
}

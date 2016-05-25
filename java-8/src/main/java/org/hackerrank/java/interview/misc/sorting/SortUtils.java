package org.hackerrank.java.interview.misc.sorting;

import java.util.Random;

public abstract class SortUtils {
    private final static Random randSequence = new Random();

    public static boolean containsNegatives(Integer[] integers) {
        int negativeCount = 0;
        for (Integer integer : integers) {
            if (integer < 0) {
                negativeCount++;
            }
        }
        return (negativeCount > 0);
    }

    public static Integer[] generateArrayOfSize(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = randSequence.nextInt(100);
        }
        return array;
    }

    public static boolean isArraySorted(Integer[] array) {
        if (array.length == 0 || array.length == 1) {
            return true;
        }
        int maxMet = array[0];
        for (int i : array) {
            if (i < maxMet) {
                return false;
            } else if (i > maxMet) {
                maxMet = i;
            }
        }
        return true;
    }
}

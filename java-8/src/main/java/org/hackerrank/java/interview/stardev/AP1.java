package org.hackerrank.java.interview.stardev;

public class AP1 {

    public static boolean scoresIncreasing(int[] scores) {
        return scanHeap(0, scores);
    }

    private static boolean scanHeap(int start, int[] array) {
        if (start >= array.length) {
            return true;
        }
        int c = array[start];
        int li = getLeftChildIndex(start);
        int ri = getRightChildIndex(start);
        return !(li < array.length && ri < array.length && array[li] > array[ri])
                && !(li < array.length && array[li] < c)
                && !(ri < array.length && array[ri] < c)
                && scanHeap(li, array)
                && scanHeap(ri, array);
    }

    private static int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    private static int getRightChildIndex(int i) {
        return 2 * (i + 1);
    }

    public static boolean scores100(int[] scores) {
        return scores100(0, scores, 0) > 0;
    }

    public static int scores100(int start, int[] scores, int counter) {
        if (start >= scores.length) {
            return counter;
        }

        if (scores[start] == 100 && start + 1 < scores.length && scores[start + 1] == 100) {
            return scores100(start + 1, scores, counter + 1);
        }
        return scores100(start + 1, scores, counter);
    }

    public static boolean scoresClump(int[] scores) {
        return scoresClump(0, scores, 0) >= 3;
    }

    public static int scoresClump(int start, int[] scores, int counter) {
        if (start >= scores.length) {
            return counter;
        }

        if (start + 2 < scores.length
                && scores[start + 1] - scores[start] <= 2
                && scores[start + 2] - scores[start + 1] <= 2
                && scores[start + 2] - scores[start] <= 2) {
            return scoresClump(start + 1, scores, counter + 3);
        }
        return scoresClump(start + 1, scores, counter);
    }

    public static int scoresAverage(int[] scores) {
        return Math.max(average(scores, 0, scores.length / 2), average(scores, scores.length / 2, scores.length));
    }


    private static int average(int[] scores, int start, int end) {
        return 0;
    }
}

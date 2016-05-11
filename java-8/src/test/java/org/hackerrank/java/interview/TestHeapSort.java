package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class TestHeapSort {
    private final static int MAX_ARRAY_LENGTH = 10000;
    private final static int MAX_NUMBER_OF_TRIAL = 100;
    private final static Random randSequence = new Random();
    private final static Heap<Integer> heap = new Heap<>();
    private final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);

    @Test
    public void testHeapSort() throws Exception {
        long averageCalculationTime = 0;
        int numberOfTrial = 0;
        for (int i = 0; i < MAX_NUMBER_OF_TRIAL; i++) {
            // input data
            Integer[] array = generateArrayOfSize(MAX_ARRAY_LENGTH);

            // build a tree

            long timeStamp = System.currentTimeMillis();
            array = heap.heapSort(array);
            long mlsSpend = System.currentTimeMillis() - timeStamp;
            averageCalculationTime += mlsSpend;
            numberOfTrial++;

            //Test array
            Assert.assertTrue(isArraySorted(array));
        }
        System.out.printf("Average time of heapsort: %d mls", averageCalculationTime / numberOfTrial);
    }

    @Test
    public void testHeapSortRaceCondition() throws Exception {
        // Prepare test data set
        final Integer[][] array = {new Integer[MAX_ARRAY_LENGTH]};
        for (int i = MAX_ARRAY_LENGTH - 1; i >= 0; i--) {
            array[0][i] = i;
        }
        // Verify input data for negatives
        Assert.assertFalse(containsNegatives(array[0]));
        // Replace random array's element value to negative.
        Future<Boolean> futureReplace = service.submit(() -> {
            array[0][randSequence.nextInt(MAX_ARRAY_LENGTH - 1)] = -1;
            return true;
        });
        // Run heap sort in the parallel thread.
        Future<Integer[]> futureSort = service.submit(() -> heap.heapSort(array[0]));
        while (!futureReplace.isDone() && !futureSort.isDone() ){
            Thread.sleep(1000);
        }
        // Negative element test
        Assert.assertTrue(containsNegatives(array[0]));
        // Test array is sorted
        Assert.assertFalse(isArraySorted(array[0]));

    }

    private boolean containsNegatives(Integer[] integers) {
        int negativeCount = 0;
        for(int i = 0; i < MAX_ARRAY_LENGTH; i++){
            if(integers[i] < 0) {
                negativeCount++;
            }
        }
        return (negativeCount > 0);
    }

    private static Integer[] generateArrayOfSize(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = randSequence.nextInt(100);
        }
        return array;
    }

    private boolean isArraySorted(Integer[] array) {
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

    @Test
    public void testArraySorted() throws Exception {
        Integer[] sortedArray = {0, 1, 1, 2};
        Assert.assertTrue(isArraySorted(sortedArray));

        Integer[] unsortedArray = {0, 1, 0, 2, 0, 1};
        Assert.assertFalse(isArraySorted(unsortedArray));

        Integer[] emptyArray = {};
        Assert.assertTrue(isArraySorted(emptyArray));

        Integer[] oneElementArray = {1};
        Assert.assertTrue(isArraySorted(oneElementArray));
    }
}

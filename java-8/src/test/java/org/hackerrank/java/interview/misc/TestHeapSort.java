package org.hackerrank.java.interview.misc;

import org.hackerrank.java.interview.misc.sorts.HeapSort;
import org.junit.Assert;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.hackerrank.java.interview.misc.sorts.SortUtils.*;

public class TestHeapSort {
    private final static int MAX_ARRAY_LENGTH = 10000;
    private final static int MAX_NUMBER_OF_TRIAL = 100;
    private final static Random randSequence = new Random();
    private final static HeapSort<Integer> HEAP_SORT = new HeapSort<>();
    private final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);

    @Test
    public void testHeapSort() throws Exception {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

        StringBuilder sb = new StringBuilder();
        sb.append("Up time (in milliseconds): ").append(runtimeMxBean.getUptime()).append("\n");
        MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memUsage = memoryMxBean.getHeapMemoryUsage();
        DecimalFormat df = new DecimalFormat("#0.00");
        String smemUsed = df.format((double)memUsage.getUsed()/(1024 * 1024));
        String smemMax = df.format((double)memUsage.getMax()/(1024 * 1024));
        sb.append("HeapSort memory usage (in MB): ").append(smemUsed).append("/").append(smemMax);
        sb.append("\n");

        long averageCalculationTime = 0;
        int numberOfTrial = 0;
        for (int i = 0; i < MAX_NUMBER_OF_TRIAL; i++) {
            // input data
            Integer[] array = generateArrayOfSize(MAX_ARRAY_LENGTH);

            // build a tree

            long timeStamp = System.currentTimeMillis();
            array = HEAP_SORT.sort(array);
            long mlsSpend = System.currentTimeMillis() - timeStamp;
            averageCalculationTime += mlsSpend;
            numberOfTrial++;

            // Track memory usage
            memUsage = memoryMxBean.getHeapMemoryUsage();
            smemUsed = df.format((double) memUsage.getUsed() / (1024 * 1024));
            smemMax = df.format((double) memUsage.getMax() / (1024 * 1024));
            sb.append("HeapSort memory usage (in MB): ").append(smemUsed).append("/").append(smemMax);
            sb.append(", time spent (mls): ").append(mlsSpend);
            sb.append("\n");

            //Test array
            Assert.assertTrue(isArraySorted(array));
        }
        sb.append(String.format("Average time of heapsort: %d mls", averageCalculationTime / numberOfTrial));
        System.out.print(sb.toString());
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
        // Run HEAP_SORT sort in the parallel thread.
        Future<Integer[]> futureSort = service.schedule(() -> HEAP_SORT.sort(array[0]), 1000, TimeUnit.MILLISECONDS);
        // Replace random array's element value to negative.
        Future<Boolean> futureReplace = service.submit(() -> {
            array[0][randSequence.nextInt(MAX_ARRAY_LENGTH - 1)] = -1;
            return true;
        });
        while (!futureReplace.isDone() && !futureSort.isDone() ){
            Thread.sleep(1000);
        }
        // Negative element test
        Assert.assertTrue(containsNegatives(array[0]));
        //Test array
        Assert.assertFalse(isArraySorted(array[0]));
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

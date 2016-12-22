package org.hackerrank.java.interview.misc.sorting;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

abstract class SortUtils {

    static void measure(Sort<Integer> sort, int maxSize, int nTrial){

        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        StringBuilder sb = new StringBuilder();
        sb.append("Measuring: ").append(sort.getClass().getCanonicalName()).append("\n");
        sb.append("Up time (in milliseconds): ").append(runtimeMxBean.getUptime()).append("\n");
        MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memUsage = memoryMxBean.getHeapMemoryUsage();
        DecimalFormat df = new DecimalFormat("#0.00");
        String smemUsed = df.format((double)memUsage.getUsed()/(1024 * 1024));
        String smemMax = df.format((double)memUsage.getMax()/(1024 * 1024));
        sb.append("Initial memory usage (in MB): ").append(smemUsed).append("/").append(smemMax);
        sb.append("\n");

        long averageCalculationTime = 0;
        int numberOfTrial = 0;
        for (int i = 0; i < nTrial; i++) {
            Integer[] array = generateArrayOfSize(maxSize);

            long timeStamp = System.currentTimeMillis();
            array = sort.sort(array);
            long mlsSpend = System.currentTimeMillis() - timeStamp;
            averageCalculationTime += mlsSpend;
            numberOfTrial++;

            memUsage = memoryMxBean.getHeapMemoryUsage();
            smemUsed = df.format((double) memUsage.getUsed() / (1024 * 1024));
            smemMax = df.format((double) memUsage.getMax() / (1024 * 1024));
            sb.append(String.format("%s : [ size: %s, memory usage: %s (MB) / memory usage max: %s (MB),  time spent %s (mls)  ]",
                    sort.getClass().getCanonicalName(), maxSize, smemUsed, smemMax, mlsSpend));
            sb.append("\n");

            if(!isArraySorted(array)){
                throw new IllegalStateException("Array is not sorted");
            }
        }
        sb.append(String.format("Average time of heapsort: %d mls", averageCalculationTime / numberOfTrial)).append("\n\n");
        System.out.print(sb.toString());
    }

    public static void sortRaceCondition(List<Sort<Integer>> sorts, int maxArrayLength){
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        Random randSequence = new Random();
        sorts.stream().forEach(sort -> {
            // Prepare test data set
            final Integer[][] array = {new Integer[maxArrayLength]};
            for (int i = maxArrayLength - 1; i >= 0; i--) {
                array[0][i] = i;
            }
            // Verify input data for negatives
            if(containsNegatives(array[0])){
                throw new IllegalStateException("Array must not contain negative values");
            }
            // Run HEAP_SORT sorting in the parallel thread.
            Future<Integer[]> futureSort = service.schedule(() -> sort.sort(array[0]), 1000, TimeUnit.MILLISECONDS);
            // Replace random array's element value to negative.
            Future<Boolean> futureReplace = service.submit(() -> {
                array[0][randSequence.nextInt(maxArrayLength - 1)] = -1;
                return true;
            });
            while (!futureReplace.isDone() && !futureSort.isDone()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Negative element test
            if(!containsNegatives(array[0])){
                throw new IllegalStateException("Array must contains negative values");
            }
            //Test array
            if(isArraySorted(array[0])){
                throw new IllegalStateException("Array must not be sorted");
            }
        });
    }

    static boolean containsNegatives(Integer[] integers) {
        int negativeCount = 0;
        for (Integer integer : integers) {
            if (integer < 0) {
                negativeCount++;
            }
        }
        return (negativeCount > 0);
    }

    private static Integer[] generateArrayOfSize(int length) {
        Random randSequence = new Random();
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = randSequence.nextInt(100);
        }
        return array;
    }

    static boolean isArraySorted(Integer[] array) {
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

    static boolean isArraySorted(byte[] array) {
        if (array.length == 0 || array.length == 1) {
            return true;
        }
        byte maxMet = array[0];
        for (byte b : array) {
            if (b < maxMet) {
                return false;
            } else if (b > maxMet) {
                maxMet = b;
            }
        }
        return true;
    }
}

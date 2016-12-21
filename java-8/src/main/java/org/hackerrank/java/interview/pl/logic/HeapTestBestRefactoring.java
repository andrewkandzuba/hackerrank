package org.hackerrank.java.interview.pl.logic;

import java.util.*;

public class HeapTestBestRefactoring {
    private static class AddFunction {
        static int calc(int x, int y) {
            return x + y;
        }
    }

    private final static int SIZE = 10000000, RANDOM_SIZE = 20;
    private final static int MB = 1024 * 1024;
    private final static Runtime runtime = Runtime.getRuntime();
    private final static Random rn = new Random(RANDOM_SIZE);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Set<String> results = getResults();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        long res = (runtime.totalMemory() - runtime.freeMemory());
        System.out.println("Used Memory:" + res / MB);

    }

    private static Set<String> getResults() {
        Set<String> results = new HashSet<>();
        // do random additions 10 million times and store the result in a list
        for (int i = 0; i < SIZE; i++) {

            int x = rn.nextInt(RANDOM_SIZE);
            int y = rn.nextInt(RANDOM_SIZE);

            int z = HeapTestBestRefactoring.AddFunction.calc(x, y);
            String result = x + "+" + y + "=" + z;
            results.add(result);
        }
        return results;
    }

}

package org.hackerrank.java.interview.pl.logic;

import java.util.*;

public class HeapTestRetainContract {
    class AddFunction {
        private final int x, y;

        public AddFunction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calc() {
            return x + y;
        }

        @Override
        public String toString() {
            return x + "+" + y + "=" + calc();
        }
    }

    private final static int SIZE = 10000000, RANDOM_SIZE = 20;
    private final static int MB = 1024 * 1024;
    private final static Runtime runtime = Runtime.getRuntime();
    private final static Random rn = new Random();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> results = getResults();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        long res = (runtime.totalMemory() - runtime.freeMemory());
        System.out.println("Used Memory:" + res / MB);

    }

    private static List<String> getResults() {
        HeapTestRetainContract test = new HeapTestRetainContract();
        List<String> results = new ArrayList<String>();
        Set<String> set = new HashSet<>();
        // do random additions 10 million times and store the result in a list
        for (int i = 0; i < SIZE; i++) {
            set.add(test.new AddFunction(rn.nextInt(RANDOM_SIZE), rn.nextInt(RANDOM_SIZE)).toString());
        }
        results.addAll(set);
        return results;
    }
}

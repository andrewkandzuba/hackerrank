package org.hackerrank.java.interview.jcp.cache;

import org.junit.Test;

import java.util.concurrent.Executors;

public class TestMemoizer {

    @Test
    public void testFibonacciComputation() throws Exception {
        Memoizer<Integer, Integer> memoizer = new Memoizer<>(new FibonacciComputable(), Executors.newSingleThreadExecutor());
        memoizer.compute(2);
        memoizer.compute(3);
        memoizer.compute(2);
    }
}

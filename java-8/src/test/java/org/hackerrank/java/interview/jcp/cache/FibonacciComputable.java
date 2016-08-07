package org.hackerrank.java.interview.jcp.cache;


import org.hackerrank.java.interview.jcp.forkjoin.FibonacciTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

class FibonacciComputable implements Computable<Integer, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(TestMemoizer.class);
    private final ForkJoinPool forkJoinPool;

    public FibonacciComputable(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    public Integer compute(Integer arg) throws InterruptedException {
        logger.info("Compute: " + arg);
        return forkJoinPool.invoke(new FibonacciTask(arg));
    }
}

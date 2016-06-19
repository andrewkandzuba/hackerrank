package org.hackerrank.java.interview.jcp.cache;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class TestMemoizer {

    private static final Logger logger = Logger.getLogger("TestMemoizer");

    @Test
    public void testFibonacciComputation() throws Exception {
        ExecutorService es = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);
        Memoizer<Integer, Integer> memoizer = new Memoizer<>(new FibonacciComputable(), es);
        CountDownLatch endGateway = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            es.submit(() -> {
                try {
                    Integer res = memoizer.compute(0);
                    logger.info(String.format("Computed value for %s is %s", 0, res));
                    endGateway.countDown();
                } catch (InterruptedException e) {
                    logger.info(e.getLocalizedMessage());
                    Thread.currentThread().isInterrupted();
                }
            });
        }
        endGateway.await();
    }
}

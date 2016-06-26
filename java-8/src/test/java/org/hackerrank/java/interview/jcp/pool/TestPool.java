package org.hackerrank.java.interview.jcp.pool;

import org.hackerrank.java.interview.jcp.pool.connection.Connection;
import org.hackerrank.java.interview.jcp.pool.connection.ThreadPoolFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TestPool {
    private static final Logger logger = LoggerFactory.getLogger(TestPool.class);

    @Test
    public void testConnectionPool() throws Exception {
        Pool<Connection> pool = new Pool<>(Connection::new);
        CountDownLatch latch = new CountDownLatch(100);
        ExecutorService es = Executors.newFixedThreadPool(10, new ThreadPoolFactory());

        new Thread(() -> {
            while (true) {
                try {
                    es.submit(() -> {

                    });
                } catch (RejectedExecutionException e) {
                    if (!es.isShutdown()) {
                        logger.error("Task submission rejected", e);
                        break;
                    }
                }
            }
        }).start();
        logger.info("Before latch");
        latch.await();
        logger.info("After latch");
        try {
            es.shutdown();
            es.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } finally {
            es.shutdown();
        }
    }
}

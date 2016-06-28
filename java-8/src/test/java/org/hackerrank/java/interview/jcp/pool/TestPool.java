package org.hackerrank.java.interview.jcp.pool;

import org.hackerrank.java.interview.jcp.pool.connection.Connection;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionReal;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionThreadFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TestPool {
    private static final Logger logger = LoggerFactory.getLogger(TestPool.class);

    @Test
    public void testConnectionPool() throws Exception {
        try (Pool<Connection> pool = new Pool<>(ConnectionReal::new)) {
            CountDownLatch latch = new CountDownLatch(100);
            ExecutorService es = Executors.newFixedThreadPool(5, ConnectionThreadFactory.createThreadFactory(pool));
            new Thread(() -> {
                while (!es.isShutdown()) {
                    try {
                        es.submit(() -> {
                            Connection c = ConnectionThreadFactory.getConnection();
                            logger.info(String.format("Do something useful with connection: %s", c.getId()));
                        });
                    } catch (RejectedExecutionException e) {
                        if (!es.isShutdown()) {
                            logger.error("Task submission rejected", e);
                        }
                        break;
                    }
                    latch.countDown();
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
}

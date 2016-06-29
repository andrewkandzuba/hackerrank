package org.hackerrank.java.interview.jcp.pool;

import org.hackerrank.java.interview.jcp.pool.connection.Connection;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionDecorator;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionReal;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionThreadFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool {
    private static final Logger logger = LoggerFactory.getLogger(TestPool.class);

    @Test
    public void testConnectionPool() throws Exception {
        final int MAX_WORKERS = 100;
        try (Pool<Connection> pool = Pool.create(ConnectionReal::new)) {
            CountDownLatch latch = new CountDownLatch(MAX_WORKERS);
            ExecutorService es = Executors.newFixedThreadPool(MAX_WORKERS, new ConnectionThreadFactory());
            new Thread(() -> {
                while (!es.isShutdown()) {
                    es.submit(() -> {
                        try (Connection c = new ConnectionDecorator(pool)) {
                            logger.info(String.format("Do something useful with connection: %s", c.getId()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    latch.countDown();
                }
            }).start();
            logger.info("Before latch");
            latch.await();
            logger.info("After latch");
            es.shutdown();
            while (!es.isTerminated()) {
                Thread.yield();
            }
        }
    }
}

package org.hackerrank.java.interview.jcp.pool;

import org.hackerrank.java.interview.jcp.cache.Pool;
import org.hackerrank.java.interview.jcp.pool.connection.Connection;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionDecorator;
import org.hackerrank.java.interview.jcp.pool.connection.ConnectionReal;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(TestConnectionPool.class);
    private static final int MAX_WORKERS = 100;

    @Test
    public void testConnectionPool() throws Exception {
        try (Pool<Connection> pool = new Pool<>(ConnectionReal::new)) {
            CountDownLatch latch = new CountDownLatch(MAX_WORKERS);
            ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2, new Pool.DaemonThreadFactory());
            new Thread(() -> {
                while (!es.isShutdown()) {
                    es.submit(() -> {
                        try (Connection c = new ConnectionDecorator(pool)) {
                            logger.info(String.format("Do something useful with connection: %s", c.getId()));
                        } catch (IOException | InterruptedException | NullPointerException e) {
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

    @Test
    public void testCompareAndSet() throws Exception {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        ExecutorService es = Executors.newFixedThreadPool(MAX_WORKERS, new Pool.DaemonThreadFactory());
        for (int i = 0; i < 100; i++) {
            es.submit(() -> {
                if (atomicBoolean.compareAndSet(false, true)) {
                    logger.info("I changed it!!!");
                }
            });
        }
        es.shutdown();
        while (!es.isTerminated()) {
            Thread.yield();
        }
    }
}

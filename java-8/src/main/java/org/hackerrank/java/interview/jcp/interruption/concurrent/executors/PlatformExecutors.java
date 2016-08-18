package org.hackerrank.java.interview.jcp.interruption.concurrent.executors;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static org.hackerrank.java.interview.jcp.interruption.concurrent.ExceptionManager.launderThrowable;

public final class PlatformExecutors {
    private final static String DEFAULT_POOL_NAME = "ServerThreadPool";
    private final static int DEFAULT_CAPACITY = 50;
    private final static double uCPU = 0.5;
    private final static double wcFactor = 0.25;

    public static ExecutorService newServerThreadPool() {
        int parallelismLevel = new BigDecimal(Runtime.getRuntime().availableProcessors())
                .multiply(BigDecimal.valueOf(uCPU))
                .multiply(BigDecimal.valueOf(1 + wcFactor)).intValue();
        return newServerThreadPool(DEFAULT_POOL_NAME, parallelismLevel, DEFAULT_CAPACITY);
    }

    public static ExecutorService newServerThreadPool(int parallelismLevel) {
        return new ServerThreadPool(DEFAULT_POOL_NAME, parallelismLevel, DEFAULT_CAPACITY);
    }

    public static ExecutorService newServerThreadPool(String poolName, int parallelismLevel, int capacity) {
        return new ServerThreadPool(poolName, parallelismLevel, capacity);
    }

    public static List<Runnable> shutdownGracefully(ExecutorService es, long timeout, TimeUnit unit) {
        List<Runnable> unprocessed = new LinkedList<>();
        es.shutdown();
        try {
            if (!es.awaitTermination(timeout, unit)) {
                unprocessed.addAll(es.shutdownNow());
                if (!es.awaitTermination(timeout, unit)) {
                    System.err.println("Pool is not shutting down!!!");
                }
            }
        } catch (InterruptedException e) {
            unprocessed.addAll(es.shutdownNow());
        }
        return Collections.unmodifiableList(unprocessed);
    }

    public static List<Runnable> shutdownGracefully(ExecutorService es) {
        return shutdownGracefully(es, 100, TimeUnit.MILLISECONDS);
    }

    public static <T> T timedRun(ExecutorService taskExec, Callable<T> c, long timeout, TimeUnit unit) throws InterruptedException {
        Future<T> f = taskExec.submit(c);
        T t = null;
        try {
            t = f.get(timeout, unit);
        } catch (TimeoutException ignored) {
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        } finally {
            f.cancel(true);
        }
        return t;
    }

    public static <T> T blockedRun(ExecutorService taskExec, Callable<T> c) throws InterruptedException {
        Future<T> f = taskExec.submit(c);
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        } finally {
            f.cancel(true);
        }
    }
}

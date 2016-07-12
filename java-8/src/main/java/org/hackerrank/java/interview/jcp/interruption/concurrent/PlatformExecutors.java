package org.hackerrank.java.interview.jcp.interruption.concurrent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static org.hackerrank.java.interview.jcp.interruption.concurrent.ExceptionManager.launderThrowable;

public final class PlatformExecutors {

    public static TrackingCancellingExecutorService newFixedTrackingCancellingExecutorService() {
        return newFixedTrackingCancellingExecutorService(Runtime.getRuntime().availableProcessors());
    }

    public static TrackingCancellingExecutorService newFixedTrackingCancellingExecutorService(int parallelismLevel) {
        return newFixedTrackingCancellingExecutorService(parallelismLevel, Executors.defaultThreadFactory());
    }

    public static TrackingCancellingExecutorService newFixedTrackingCancellingExecutorService(int parallelismLevel, ThreadFactory threadFactory) {
        return new TrackingCancellingExecutorService(parallelismLevel, parallelismLevel, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
    }

    public static TrackingCancellingExecutorService newSingleThreadTrackingCancellingExecutorService() {
        return newFixedTrackingCancellingExecutorService(1);
    }

    public static TrackingCancellingExecutorService newSingleThreadTrackingCancellingExecutorService(ThreadFactory threadFactory) {
        return newFixedTrackingCancellingExecutorService(1, threadFactory);
    }

    public static List<Runnable> shutdownGracefully(ExecutorService es) {
        List<Runnable> unprocessed = new LinkedList<>();
        es.shutdown();
        try {
            if (!es.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                unprocessed.addAll(es.shutdownNow());
                if (!es.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    System.err.println("Pool is not shutting down!!!");
                }
            }
        } catch (InterruptedException e) {
            unprocessed.addAll(es.shutdownNow());
        }
        return Collections.unmodifiableList(unprocessed);
    }

    public static <T> T timedRun(ExecutorService taskExec, Callable<T> c, long timeout, TimeUnit unit) throws InterruptedException {
        Future<T> f = taskExec.submit(c);
        T t = null;
        try {
            t = f.get(timeout, unit);
        } catch (TimeoutException ignored){
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

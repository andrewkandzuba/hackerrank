package org.hackerrank.java.interview.jcp.interruption.concurrent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class PlatformExecutors {

    public static TrackingCancellingExecutorService newFixedTrackingCancellingExecutorService(){
        return newFixedTrackingCancellingExecutorService(Runtime.getRuntime().availableProcessors());
    }

    public static TrackingCancellingExecutorService newFixedTrackingCancellingExecutorService(int parallelismLevel){
        return new TrackingCancellingExecutorService(
                parallelismLevel, parallelismLevel, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    public static List<Runnable> shutdownGracefully(ExecutorService es){
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
}

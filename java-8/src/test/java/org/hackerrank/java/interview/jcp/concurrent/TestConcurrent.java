package org.hackerrank.java.interview.jcp.concurrent;

import org.hackerrank.java.interview.jcp.interruption.concurrent.ExecutorServiceShutdown;
import org.hackerrank.java.interview.jcp.interruption.concurrent.TrackingExecutorService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrent {

    @Test
    public void testExecutionServiceInterruption() throws Exception {
        final AtomicInteger counter = new AtomicInteger();
        final ExecutorService service = Executors.newSingleThreadExecutor();

        service.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(String.format("counter = %s", counter.getAndIncrement()));
            }
        });
        ExecutorServiceShutdown.shutdownGracefully(service);
    }

    @Test
    public void testTrackingExecutionService() throws Exception {
        int parallelismLevel = Runtime.getRuntime().availableProcessors();
        final TrackingExecutorService es = new TrackingExecutorService(Executors.newFixedThreadPool(parallelismLevel));
        final TransferQueue<Integer> queue = new LinkedTransferQueue<>();

        int nTasks = parallelismLevel * 2;
        while (nTasks-- > 0) {
            int finalNTasks = nTasks;
            es.execute(() -> {
                try {
                    System.out.println("Putter thread interrupted status is : " + Thread.currentThread().isInterrupted());
                    System.out.println("Putter thread state is : " + Thread.currentThread().getState());
                    System.out.println("Starts the transfer");
                    queue.transfer(finalNTasks);
                    System.out.println("Transfer is completed");
                } catch (InterruptedException e) {
                    System.out.println("Putter thread interrupted status inside InterruptedException block is : " + Thread.currentThread().isInterrupted());
                    System.out.println("Putter thread state inside InterruptedException block is : " + Thread.currentThread().getState());
                    Thread.currentThread().interrupt();
                    System.out.println("Putter thread interrupted status inside InterruptedException block is : " + Thread.currentThread().isInterrupted());
                    System.out.println("Putter thread state inside InterruptedException block is : " + Thread.currentThread().getState());
                }
            });
        }
        queue.take();

        List<Runnable> unprocessed = ExecutorServiceShutdown.shutdownGracefully(es);
        Set<Runnable> cancelledAndShutdown = es.getTasksCancelledAndShutdown();
        Assert.assertEquals(parallelismLevel - 1, unprocessed.size());
        Assert.assertEquals(parallelismLevel, cancelledAndShutdown.size());
    }
}

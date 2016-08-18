package org.hackerrank.java.interview.jcp.future;

import org.hackerrank.java.interview.jcp.futures.Router;
import org.hackerrank.java.interview.jcp.interruption.concurrent.executors.PlatformExecutors;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hackerrank.java.interview.jcp.interruption.concurrent.executors.PlatformExecutors.newServerThreadPool;

public class TestCompletableFuture {

    @Test
    public void testCompletableFutureContract() throws Exception {
        final CompletableFuture<Integer> fc = new CompletableFuture<>();
        Assert.assertFalse(fc.isDone());
        Assert.assertTrue(fc.complete(43));
        Assert.assertTrue(fc.isDone());
        Assert.assertFalse(fc.complete(44));

        final CompletableFuture<Integer> fex = new CompletableFuture<>();
        Assert.assertFalse(fex.isDone());
        Assert.assertTrue(fex.completeExceptionally(new CancellationException()));
        Assert.assertTrue(fex.isDone());
    }

    @Test
    public void testCompletableFutureStream() throws Exception {
        int parallelismLevel = Runtime.getRuntime().availableProcessors();
        ExecutorService es = newServerThreadPool(parallelismLevel + 2);
        final BlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
        final AtomicInteger counter = new AtomicInteger();
        // Producers
        for(int i = 0; i < parallelismLevel; i++) {
            es.execute(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        counter.getAndIncrement();
                        queue.offer(counter.getAndIncrement());
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
        }
        // Router
        Router<Integer> integerRouter = new Router<>(queue);
        integerRouter.subscribe(i -> i % 2 == 0, i -> System.out.println("Even: " +  i));
        integerRouter.subscribe(i -> i % 2 > 0, i -> System.out.println("Odd: " +  i));
        es.execute(integerRouter);
        TimeUnit.SECONDS.sleep(2);
        PlatformExecutors.shutdownGracefully(es);
    }
}

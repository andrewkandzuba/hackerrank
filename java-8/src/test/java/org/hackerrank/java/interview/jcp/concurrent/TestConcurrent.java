package org.hackerrank.java.interview.jcp.concurrent;

import org.hackerrank.java.interview.jcp.interruption.concurrent.TrackingCancellingExecutorService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hackerrank.java.interview.jcp.interruption.concurrent.ExceptionManager.launderThrowable;
import static org.hackerrank.java.interview.jcp.interruption.concurrent.PlatformExecutors.*;

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
        shutdownGracefully(service);
    }

    @Test
    public void testTrackingExecutionService() throws Exception {
        int parallelismLevel = Runtime.getRuntime().availableProcessors();
        final TrackingCancellingExecutorService es = newFixedTrackingCancellingExecutorService(parallelismLevel);
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

        List<Runnable> unprocessed = shutdownGracefully(es);
        Set<Runnable> cancelledAndShutdown = es.getTasksCancelledAndShutdown();
        Assert.assertEquals(parallelismLevel - 1, unprocessed.size());
        Assert.assertEquals(parallelismLevel, cancelledAndShutdown.size());
    }


    @Test
    public void testCancellationRun() throws Exception {
        final TrackingCancellingExecutorService es = newSingleThreadTrackingCancellingExecutorService(new UEHThreadFactory());
        SocketMock sm = new SocketMock();
        SocketMockUsingTask<Integer> task = new SocketMockUsingTask<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sm.read(new byte[1024]);
            }
        };
        task.setSocketMock(sm);
        Integer i = timedRun(es, task, 100, TimeUnit.MILLISECONDS);
        Assert.assertNull(i);
        shutdownGracefully(es);
    }

    @Test
    public void testBlockedRun() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final TrackingCancellingExecutorService es = newFixedTrackingCancellingExecutorService(2, new UEHThreadFactory());
        SocketMock sm = new SocketMock();
        SocketMockUsingTask<Integer> task = new SocketMockUsingTask<Integer>() {
            @Override
            public Integer call() throws Exception {
                latch.countDown();
                return sm.read(new byte[1024]);
            }
        };
        task.setSocketMock(sm);
        es.execute(() -> {
            try {
                latch.await();
                task.cancel();
            } catch (InterruptedException ignored) {
            }
        });
        boolean exceptionHandled = false;
        Integer i = null;
        try {
            i = blockedRun(es, task);
        } catch (IllegalStateException e) {
            exceptionHandled = true;
            e.printStackTrace();
        } finally {
            Assert.assertNull(i);
            Assert.assertTrue(exceptionHandled);
            shutdownGracefully(es);
        }
    }

    @Test
    public void testUncaughtExceptionHandler() throws Exception {
        int parallelismLevel = Runtime.getRuntime().availableProcessors();
        TrackingCancellingExecutorService es = newFixedTrackingCancellingExecutorService(parallelismLevel + 1, new UEHThreadFactory());
        final CountDownLatch latch = new CountDownLatch(parallelismLevel);
        final CountDownLatch finalization = new CountDownLatch(1);
        SocketMock sm = new SocketMock();
        for (int i = 0; i < parallelismLevel; i++) {
            es.execute(() -> {
                byte[] buffer = new byte[1024];
                try {
                    latch.countDown();
                    sm.read(buffer);
                } catch (SocketMockException e) {
                    throw launderThrowable(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        es.execute(() -> {
            try {
                latch.await();
                sm.close();
            } catch (InterruptedException | IOException ignored) {
            } finally {
                finalization.countDown();
            }
        });
        finalization.await();
        shutdownGracefully(es);
    }
}

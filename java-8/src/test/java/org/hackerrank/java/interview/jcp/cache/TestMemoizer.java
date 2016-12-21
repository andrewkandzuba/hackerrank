package org.hackerrank.java.interview.jcp.cache;

import org.hackerrank.java.interview.jcp.forkjoin.FibonacciTask;
import org.hackerrank.java.interview.jcp.interruption.concurrent.executors.PlatformExecutors;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class TestMemoizer {
    @Test
    public void testFibonacciComputation() throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() + 1);
        Assert.assertEquals(0, forkJoinPool.invoke(new FibonacciTask(0)).intValue());
        Assert.assertEquals(1, forkJoinPool.invoke(new FibonacciTask(1)).intValue());
        Assert.assertEquals(1, forkJoinPool.invoke(new FibonacciTask(2)).intValue());
        Assert.assertEquals(2, forkJoinPool.invoke(new FibonacciTask(3)).intValue());
        Assert.assertEquals(3, forkJoinPool.invoke(new FibonacciTask(4)).intValue());
        Assert.assertEquals(5, forkJoinPool.invoke(new FibonacciTask(5)).intValue());
        Assert.assertEquals(8, forkJoinPool.invoke(new FibonacciTask(6)).intValue());

        Memoizer<Integer, Integer> memoizer = new Memoizer<>(new FibonacciComputable(forkJoinPool));
        Assert.assertEquals(1, memoizer.compute(2).intValue());
        Assert.assertEquals(2, memoizer.compute(3).intValue());
        Assert.assertEquals(1, memoizer.compute(2).intValue());
        PlatformExecutors.shutdownGracefully(forkJoinPool);
    }
}

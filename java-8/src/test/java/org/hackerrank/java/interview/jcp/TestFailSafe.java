package org.hackerrank.java.interview.jcp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFailSafe {
    private final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);

    @Test(expected = ConcurrentModificationException.class)
    public void testMapFailFast() throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 10000; i++){
            map.put(i, i);
        }
        AtomicInteger counter = new AtomicInteger(map.size());
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        Future<Boolean> f = service.submit(() -> {
            map.clear();
            return true;
        });
        while (!f.isDone() && !f.isCancelled()){
            Thread.yield();
        }
        it.forEachRemaining(integerIntegerEntry -> counter.decrementAndGet());
        Assert.assertEquals(0, counter.get());
    }

    @Test
    public void testMapFailSafeButDataIsLost() throws Exception {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for(int i = 0; i < 10000; i++){
            map.put(i, i);
        }
        AtomicInteger counter = new AtomicInteger(map.size());
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        Future<Boolean> f = service.submit(() -> {
            map.clear();
            return true;
        });
        while (!f.isDone() && !f.isCancelled()){
            Thread.yield();
        }
        it.forEachRemaining(integerIntegerEntry -> counter.decrementAndGet());
        Assert.assertEquals(9999, counter.get());
    }

    @Test
    public void testConcurrentHashMapFailSafe() throws Exception {
        Set<Integer> set = new CopyOnWriteArraySet<>();
        for(int i = 0; i < 10000; i++){
            set.add(i);
        }
        AtomicInteger counter = new AtomicInteger(set.size());
        Iterator<Integer> it = set.iterator();
        Future<Boolean> f = service.submit(() -> {
            set.clear();
            return true;
        });
        while (!f.isDone() && !f.isCancelled()){
            Thread.yield();
        }
        it.forEachRemaining(integerIntegerEntry -> counter.decrementAndGet());
        Assert.assertEquals(0, counter.get());
    }
}

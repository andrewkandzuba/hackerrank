package org.hackerrank.java.interview.jcp.queues;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueProducer implements Runnable {
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return SynchronousQueueProcessor.nextId.getAndIncrement();
                }
            };
    private final static Random random = new Random();
    private final BlockingQueue<String> queue;

    public SynchronousQueueProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String m = String.format("message-%s", random.nextInt(1024));
                if (queue.offer(m, 5000, TimeUnit.MILLISECONDS)) {
                    System.out.println(String.format("[Thread %s] has sent a message: [%s]", threadId.get(), m));
                    continue;
                }
                System.out.println(String.format("[Thread-%s] has not sent a message due to the lack of the capacity", threadId.get()));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

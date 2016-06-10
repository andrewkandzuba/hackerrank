package org.hackerrank.java.interview.jcp.queues;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SynchronousQueueConsumer implements Runnable {
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override protected Integer initialValue() {
                    return SynchronousQueueProcessor.nextId.getAndIncrement();
                }
            };
    private final BlockingQueue<String> queue;
    private final static Random random = new Random();

    public SynchronousQueueConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String m = queue.take();
                System.out.println(String.format("Thread [%s] has recived a message: [%s]", threadId.get(), m));
                Thread.sleep(random.nextInt(5000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

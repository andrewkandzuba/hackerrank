package org.hackerrank.java.interview.jcp.queues;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronousQueueProcessor implements Runnable {
    public final static AtomicInteger nextId = new AtomicInteger(0);
    private final BlockingQueue<String> queue;
    private final Executor executor;

    public SynchronousQueueProcessor() {
        this.queue = new SynchronousQueue<>();
        this.executor =  new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);
    }

    @Override
    public void run() {
        executor.execute(new SynchronousQueueConsumer(queue));
        for(int i = 0; i < 10; i++){
            executor.execute(new SynchronousQueueProducer(queue));
        }
    }

    public static void main(String... args){
        new Thread(new SynchronousQueueProcessor()).start();
    }
}

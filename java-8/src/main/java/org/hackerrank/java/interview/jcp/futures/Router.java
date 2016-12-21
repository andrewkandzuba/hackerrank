package org.hackerrank.java.interview.jcp.futures;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Router<E> implements Runnable, Closeable {
    private final BlockingQueue<E> firehose;
    private final Map<Predicate<? super E>, Consumer<E>> consumers;

    public Router(BlockingQueue<E> firehose) {
        this.firehose = firehose;
        this.consumers = new ConcurrentHashMap<>();
    }

    public void subscribe(Predicate<? super E> p, Consumer<E> c){
        consumers.putIfAbsent(p, c);
    }

    public void unsubscribe(Predicate<? super E> p){
        consumers.remove(p);
    }

    @Override
    public void close() throws IOException {
        consumers.clear();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                E e = firehose.take();
                consumers.entrySet().stream().filter(pc -> pc.getKey().test(e)).forEach(pc -> pc.getValue().accept(e));
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

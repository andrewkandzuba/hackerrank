package org.hackerrank.java.interview.jcp.pool;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.*;

public class Pool<P extends Poolable> implements Closeable {
    private final PoolableFactory<P> factory;
    private final ScheduledExecutorService executorService;
    private final BlockingDeque<P> unused;
    private final Semaphore permit;

    static <E extends Poolable> Pool<E> create(PoolableFactory<E> factory) {
        Pool<E> pool = new Pool<>(factory, Runtime.getRuntime().availableProcessors());
        pool.producer();
        return pool;
    }

    static <E extends Poolable> Pool<E> create(PoolableFactory<E> factory, int capacity) {
        Pool<E> pool = new Pool<>(factory, capacity);
        pool.producer();
        return pool;
    }

    private Pool(PoolableFactory<P> factory, int capacity) {
        this.factory = factory;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.unused = new LinkedBlockingDeque<>();
        this.permit = new Semaphore(capacity);
    }

    public P issue() throws InterruptedException, PoolableRetrieveException {
        P p = null;
        while (!executorService.isShutdown()) {
            p = unused.pollFirst();
            if(p != null){
                if (!p.isValid()) {
                    cleanUp(p);
                    continue;
                }
                break;
            }
        }
        return p;
    }

    public P issue(long timeout, TimeUnit unit) throws InterruptedException, PoolableRetrieveException {
        while (!unused.isEmpty()) {
            P p = unused.pollFirst(timeout, unit);
            if (p != null) {
                if (!p.isValid()) {
                    cleanUp(p);
                    continue;
                }
                return p;
            }
        }
        throw new PoolableRetrieveException("Zero connections are currently available!");
    }

    public void release(P p) {
        executorService.submit(() -> {
            if (!unused.offerFirst(p)) {
                cleanUp(p);
            }
        });
    }

    @Override
    public void close() throws IOException {
        try {
            executorService.shutdown();
            executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
        unused.stream().forEach(this::cleanUp);
    }

    private void cleanUp(P p) {
        try {
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            permit.release();
        }
    }

    private void producer() {
        executorService.submit(() -> {
            while (!executorService.isShutdown()) {
                if (permit.tryAcquire()) {
                    boolean wasAdded = false;
                    P p = null;
                    try {
                        p = factory.create();
                        wasAdded = unused.offerFirst(p);
                    } finally {
                        if (!wasAdded) {
                            cleanUp(p);
                        }
                    }
                }
            }
        });
    }
}

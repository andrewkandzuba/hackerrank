package org.hackerrank.java.interview.jcp.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.*;

public class Pool<P extends Poolable> implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(Pool.class);

    private final PoolableFactory<P> factory;
    private final ScheduledExecutorService executorService;
    private final BlockingDeque<P> unused;
    private final Set<P> all;

    public static <E extends Poolable> Pool<E> create(PoolableFactory<E> factory) {
        Pool<E> pool = new Pool<>(factory);
        pool.gc();
        return pool;
    }

    private Pool(PoolableFactory<P> factory) {
        this.factory = factory;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.unused = new LinkedBlockingDeque<>();
        this.all = new ConcurrentSkipListSet<P>();
    }

    public P issue() throws InterruptedException {
        P p = null;
        while (true) {
            try {
                p = unused.pollFirst(100, TimeUnit.MILLISECONDS);
                if (p == null) {
                    executorService.submit(() -> {
                        P pp = factory.create();
                        all.add(pp);
                        unused.offer(pp);
                    });
                } else if (p.isValid()) {
                    break;
                }
            } catch (CancellationException e) {
                cleanUp(p);
            }
        }
        return p;
    }

    public void release(P p) {
        executorService.submit(() -> {
            if (unused.offerFirst(p)) {
                logger.info("Instance has been returned to the pool" + p);
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
        all.stream().forEach(this::cleanUp);
    }

    private void gc() {
        executorService.schedule(() -> all.stream().filter(p -> !p.isValid()).forEach(this::cleanUp), 10000, TimeUnit.MILLISECONDS);
    }

    private void cleanUp(P p) {
        if (p != null) {
            logger.info("Clean up instance: " + p);
            try {
                p.close();
                all.remove(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

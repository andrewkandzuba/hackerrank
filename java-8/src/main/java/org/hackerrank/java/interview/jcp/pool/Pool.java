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
    private final ExecutorService executorService;
    private final BlockingDeque<P> unused;
    private final Set<P> all;

    public Pool(PoolableFactory<P> factory) {
        this.factory = factory;
        this.executorService = Executors.newSingleThreadExecutor();
        this.unused = new LinkedBlockingDeque<>();
        this.all = new CopyOnWriteArraySet<>();
    }

    public P issue() {
        try {
            while (!unused.isEmpty()) {
                P p = unused.pollFirst(100, TimeUnit.MILLISECONDS);
                if (p == null) {
                    break;
                }
                if (p.isValid()) {
                    return p;
                }
            }
            FutureTask<P> fp = new FutureTask<>(() -> {
                P p = factory.create();
                all.add(p);
                return p;
            });
            executorService.submit(fp);
            return fp.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Unable to return instance", e);
        }
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
        all.stream().filter(Poolable::isValid).forEach(p -> {
            logger.info("Clean up instance: " + p);
            try {
                p.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        all.clear();
    }
}

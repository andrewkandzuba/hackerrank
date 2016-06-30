package org.hackerrank.java.interview.jcp.pool;

import org.hackerrank.java.interview.jcp.cache.Cacheable;
import org.hackerrank.java.interview.jcp.cache.Computable;
import org.hackerrank.java.interview.jcp.cache.Memoizer;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.hackerrank.java.interview.jcp.utils.ExceptionsManager.launderThrowable;

public class Pool<P extends Cacheable> implements Closeable {
    private final int capacity;
    private final ExecutorService executorService;
    private final HolderMemoizer memoizer;

    Pool(PooledFactory<P> factory) {
        this.capacity = 1;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.memoizer = new HolderMemoizer(arg -> new Holder<>(factory.create()), this.executorService);
    }

    Pool(PooledFactory<P> factory, int capacity) {
        this.capacity = capacity;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.memoizer = new HolderMemoizer(arg -> new Holder<>(factory.create()), this.executorService);
    }

    public P take() throws InterruptedException {
        while (true) {
            for (int i = 0; i < capacity; i++) {
                Holder<P> hp = memoizer.compute(i);
                if (hp.state.compareAndSet(Holder.FREE, Holder.BUSY)) {
                    if (hp.value.isExpired()) {
                        memoizer.expire(i, ph -> cleanUp(ph.value));
                        continue;
                    }
                    return hp.value;
                }
            }
        }
    }

    public void release(P p) throws InterruptedException {
        for (int i = 0; i < capacity; i++) {
            Holder<P> hp = memoizer.compute(i);
            if (hp.value.equals(p)) {
                if (hp.state.compareAndSet(Holder.BUSY, Holder.FREE)) {
                    return;
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                } else {
                    for (int i = 0; i < capacity; i++) {
                        memoizer.cleanUp(i, pHolder -> cleanUp(pHolder.value));
                    }
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void cleanUp(P p) {
        try {
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface PooledFactory<P extends Cacheable> {
        P create();
    }

    private static class Holder<P> {
        static final int FREE = 0;
        static final int BUSY = 1;

        private final AtomicInteger state;
        private final P value;

        Holder(P value) {
            this.state = new AtomicInteger(FREE);
            this.value = value;
        }
    }

    private class HolderMemoizer extends Memoizer<Integer, Holder<P>> {

        HolderMemoizer(Computable<Integer, Holder<P>> c, ExecutorService es) {
            super(c, es);
        }

        void expire(final Integer arg, Consumer<Holder<P>> cleaner) throws InterruptedException {
            while (true) {
                Future<Holder<P>> old = cache.get(arg);
                if (old != null) {
                    Callable<Holder<P>> eval = () -> c.compute(arg);
                    FutureTask<Holder<P>> ft = new FutureTask<>(eval);
                    if (cache.replace(arg, old, ft)) {
                        es.submit(ft);
                        try {
                            cleaner.accept(old.get());
                            ft.get();
                            return;
                        } catch (CancellationException e) {
                            cache.remove(arg, ft);
                        } catch (ExecutionException e) {
                            throw launderThrowable(e.getCause());
                        }
                    }
                }
            }
        }

        void cleanUp(final Integer arg, Consumer<Holder<P>> cleaner) throws InterruptedException {
            while (true) {
                Future<Holder<P>> old = cache.remove(arg);
                if (old != null) {
                    try {
                        cleaner.accept(old.get());
                        return;
                    } catch (CancellationException e) {
                        cache.remove(arg, old);
                    } catch (ExecutionException e) {
                        throw launderThrowable(e.getCause());
                    }
                }
            }
        }
    }
}

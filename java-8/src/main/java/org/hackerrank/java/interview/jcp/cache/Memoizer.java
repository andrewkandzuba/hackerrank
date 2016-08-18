package org.hackerrank.java.interview.jcp.cache;


import java.util.concurrent.*;

import static org.hackerrank.java.interview.jcp.interruption.concurrent.ExceptionManager.launderThrowable;

public class Memoizer<A, V> implements Computable<A, V> {
    protected final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    protected final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }
}

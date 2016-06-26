package org.hackerrank.java.interview.jcp.pool;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Pool<P extends Poolable> implements Closeable {
    private final BlockingDeque<P> unused;
    private final PoolableFactory<P> factory;

    private final ReentrantReadWriteLock lock;
    private volatile boolean closed;

    public Pool(PoolableFactory<P> factory) {
        this.unused = new LinkedBlockingDeque<>();
        this.factory = factory;

        this.closed = false;
        this.lock = new ReentrantReadWriteLock();
    }

    public P issue() {
        lock.readLock().lock();
        try {
            if (closed) {
                throw new IllegalStateException("Pool is closed");
            }
            P p;
            while (!unused.isEmpty()) {
                try {
                    if ((p = unused.pollFirst(100, TimeUnit.MILLISECONDS)) == null) {
                        break;
                    }
                    if (p.isValid()) {
                        return p;
                    } else {
                        try {
                            p.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException("Unable to return Poolable instance", e);
                }
            }
            return factory.create();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void release(P p) {
        lock.readLock().unlock();
        try {
            if (closed) {
                throw new IllegalStateException("Pool is closed");
            }
            if (p.isValid() && !unused.offerFirst(p)) {
                try {
                    p.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void close() throws IOException {
        List<P> ps = new ArrayList<>();
        lock.writeLock().lock();
        try {
            if (closed) {
                throw new IllegalStateException("Pool is closed");
            }
            closed = true;
            unused.drainTo(ps);
        } finally {
            lock.writeLock().unlock();
        }
        for (P p : ps) {
            try {
                p.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

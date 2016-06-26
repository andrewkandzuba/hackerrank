package org.hackerrank.java.interview.jcp.pool;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Pool<P extends Poolable> implements Closeable {
    private final BlockingQueue<P> unused;
    private final Set<P> all;
    private final PoolableFactory<P> factory;

    private final ReentrantLock lock;
    private volatile boolean isClosed;

    public Pool(PoolableFactory<P> factory) {
        this.unused = new LinkedBlockingQueue<>();
        this.all = new HashSet<>();
        this.factory = factory;

        this.lock = new ReentrantLock();
        this.isClosed = false;
    }

    public P issue() {
        if (isClosed) {
            throw new IllegalStateException("Pool is closed");
        }
        P p;
        if (!unused.isEmpty()) {
            while (!unused.isEmpty()) {
                try {
                    if ((p = unused.poll(100, TimeUnit.MILLISECONDS)) == null) {
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
                    throw new IllegalStateException("Unable to return poolable instance", e);
                }
            }
        }
        lock.lock();
        try {
            if (isClosed) {
                throw new IllegalStateException("Pool is closed");
            }
            p = factory.create();
            all.add(p);
            return p;
        } finally {
            lock.unlock();
        }
    }

    public void release(P p) {
        if (!unused.offer(p)) {
            try {
                p.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws IOException {
        lock.lock();
        try {
            if (!isClosed) {
                for (P p : all) {
                    try {
                        p.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                isClosed = true;
            }
        } finally {
            lock.unlock();
        }
    }
}

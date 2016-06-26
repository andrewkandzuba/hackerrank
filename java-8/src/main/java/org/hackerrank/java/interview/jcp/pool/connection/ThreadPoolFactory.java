package org.hackerrank.java.interview.jcp.pool.connection;

import org.hackerrank.java.interview.jcp.pool.Pool;

import java.util.concurrent.ThreadFactory;

public class ThreadPoolFactory implements ThreadFactory {
    private static final Pool<Connection> pool = new Pool<>(Connection::new);
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue() {
            return pool.issue();
        }
    };

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(){
            @Override
            public void run() {
                CONNECTION_THREAD_LOCAL.set(pool.issue());
                r.run();
                pool.release(CONNECTION_THREAD_LOCAL.get());
                CONNECTION_THREAD_LOCAL.remove();
            }
        };
    }
}

package org.hackerrank.java.interview.jcp.pool.connection;

import org.hackerrank.java.interview.jcp.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

public class ConnectionThreadFactory implements ThreadFactory {
    private static final Logger logger  = LoggerFactory.getLogger(ConnectionThreadFactory.class);
    private final static ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    public static ThreadFactory createThreadFactory(Pool<Connection> pool){
        return new ConnectionThreadFactory(pool);
    }

    public static Connection getConnection(){
        return CONNECTION_THREAD_LOCAL.get();
    }

    private final Pool<Connection> pool;

    private ConnectionThreadFactory(Pool<Connection> pool) {
        this.pool = pool;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(){
            @Override
            public void run() {
                try(Connection c = new ConnectionDecorator(pool)) {
                    logger.info(String.format("Start new executor's thread with connection: %s", c.getId()));
                    CONNECTION_THREAD_LOCAL.set(c);
                    r.run();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    CONNECTION_THREAD_LOCAL.remove();
                    logger.info("Finish executor's thread");
                }
            }
        };
    }

    private static class ConnectionDecorator implements Connection {
        private final Pool<Connection> pool;
        private final Connection connection;

        private ConnectionDecorator(Pool<Connection> pool) {
            this.pool = pool;
            this.connection = pool.issue();
        }

        @Override
        public void close() throws IOException {
            pool.release(connection);
        }

        @Override
        public boolean isValid() {
            return connection.isValid();
        }

        @Override
        public int getId() {
            return connection.getId();
        }
    }

}

package org.hackerrank.java.interview.jcp.pool;

public interface PoolableFactory<P extends Poolable> {
    P create();
}

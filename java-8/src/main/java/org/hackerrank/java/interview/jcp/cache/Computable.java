package org.hackerrank.java.interview.jcp.cache;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

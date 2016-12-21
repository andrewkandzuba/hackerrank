package org.hackerrank.java.interview.jcp.cache;

public interface CacheableFactory<P extends Cacheable> {
    P create();
}

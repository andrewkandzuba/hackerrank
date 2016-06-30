package org.hackerrank.java.interview.jcp.cache;

import java.io.Closeable;

public interface Cacheable extends Closeable, Comparable {
    boolean isExpired();
    int getId();
}

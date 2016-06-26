package org.hackerrank.java.interview.jcp.pool;

import java.io.Closeable;

public interface Poolable extends Closeable {
    boolean isValid();
}

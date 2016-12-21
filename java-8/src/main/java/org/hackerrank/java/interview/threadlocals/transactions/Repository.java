package org.hackerrank.java.interview.threadlocals.transactions;

public interface Repository<K, V> {

    K add(V v);
    void update(K k, V v);


}

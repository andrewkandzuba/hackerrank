package org.hackerrank.java.interview.misc.sorts;

public interface Sort <T extends Comparable<? super T>> {
    T[] sort(T[] array);
}

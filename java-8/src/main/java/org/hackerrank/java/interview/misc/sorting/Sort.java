package org.hackerrank.java.interview.misc.sorting;

public interface Sort <T extends Comparable<? super T>> {
    T[] sort(T[] array);
}

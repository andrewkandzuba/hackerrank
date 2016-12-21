package org.hackerrank.java.interview.misc.sorting;

interface Sort <T extends Comparable<T>> {
    T[] sort(T[] array);
    T[] sort(T[] array, int offset, int length);
}

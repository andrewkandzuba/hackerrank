package org.hackerrank.java.interview.misc.sorting;

interface Sort <T extends Comparable<T>> {
    T[] sort(T[] array);
}

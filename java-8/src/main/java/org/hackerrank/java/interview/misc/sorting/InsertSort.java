package org.hackerrank.java.interview.misc.sorting;

class InsertSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public T[] sort(T[] array) {
        return sort(array, 0, array.length);
    }

    @Override
    public T[] sort(T[] array, int offset, int length) {
        for (int p = offset; p < length; p++) {
            T tmp = array[p];
            int j;
            for (j = p; j > 0 && tmp.compareTo(array[j - 1]) < 0; j--)
                array[j] = array[j - 1];
            array[j] = tmp;
        }
        return array;
    }
}

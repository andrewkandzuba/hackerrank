package org.hackerrank.java.interview.misc.sorting;


public class QuickSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public T[] sort(T[] array) {
        sort(array, 0, array.length - 1);
        return array;
    }

    @Override
    public T[] sort(T[] array, int from, int length) {
        if (length <= from) {
            return array;
        }
        int i = from, j = length;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i].compareTo(array[cur]) <= 0)) {
                i++;
            }
            while (j > cur && (array[cur].compareTo(array[j]) <= 0)) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        sort(array, from, cur - 1);
        sort(array, cur + 1, length);
        return array;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

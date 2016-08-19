package org.hackerrank.java.interview.misc.sorting;


public class QuickSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public T[] sort(T[] array) {
        sort(array, 0, array.length - 1);
        return array;
    }

    private void sort(T[] array, int from, int to) {
        if (to - from == 0) {
            return;
        }
        int i = from, j = to;
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
        sort(array, from, cur);
        sort(array, cur + 1, to);
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

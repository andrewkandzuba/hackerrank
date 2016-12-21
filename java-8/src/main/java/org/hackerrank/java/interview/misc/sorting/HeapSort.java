package org.hackerrank.java.interview.misc.sorting;

class HeapSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public T[] sort(T[] array) {
        // Nothing to sorting
        if (array.length == 0 || array.length == 1) {
            return array;
        }
        // HeapSort
        buildHeap(0, array, array.length);
        int l = array.length;
        do {
            swap(0, --l, array);
            reHeap(0, array, l);
        } while (l > 0);
        return array;
    }

    private void buildHeap(int current, T[] array, int length) {
        if (current < length) {
            // build current
            exchangeValue(getParentIndex(current), current, array);
            // build left subtree
            buildHeap(getLeftChildIndex(current), array, length);
            // build right subtree
            buildHeap(getRightChildIndex(current), array, length);
        }
    }

    private void reHeap(int parent, T[] array, int length) {
        int leftChildIndex = getLeftChildIndex(parent);
        int rightChildIndex = getRightChildIndex(parent);
        if (leftChildIndex < length && array[leftChildIndex].compareTo(array[parent]) > 0) {
            swap(parent,leftChildIndex, array);
            reHeap(leftChildIndex, array, length);
        }
        if (rightChildIndex < length && array[rightChildIndex].compareTo(array[parent]) > 0) {
            swap(parent, rightChildIndex, array);
            reHeap(rightChildIndex, array, length);
        }
    }

    private void exchangeValue(int parent, int current, T[] array) {
        if (parent >= 0 && current >= 0) {
            if (array[current].compareTo(array[parent]) > 0) {
                swap(parent, current, array);
                exchangeValue(getParentIndex(parent), parent, array);
            }
        }
    }

    private void swap(int parent, int current, T[] array) {
        T v = array[current];
        array[current] = array[parent];
        array[parent] = v;
    }

    private static int getParentIndex(int i) {
        return ((i - 1) / 2);
    }

    private static int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    private static int getRightChildIndex(int i) {
        return 2 * (i + 1);
    }
}

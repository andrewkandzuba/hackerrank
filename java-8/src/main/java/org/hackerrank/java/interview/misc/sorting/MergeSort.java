package org.hackerrank.java.interview.misc.sorting;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class MergeSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public T[] sort(T[] array) {
        return sort(array, 0, array.length);
    }

    @Override
    public T[] sort(T[] array, int offset, int length) {
        MergeAction fb = new MergeAction<>(array, offset, length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(fb);
        return array;
    }

    private static class MergeAction<T extends Comparable<? super T>> extends RecursiveAction {
        private final static int mSize = 10;
        private final T[] array;
        private final int from;
        private final int to;

        MergeAction(T[] array, int from, int to) {
            this.array = array;
            this.from = from;
            this.to = to;
        }

        void computeDirectly(T[] array, int from, int to) {
            Arrays.sort(array, from, to);
        }

        void merge(T[] array, int from, int split, int to) {
            // @ToDo: merge after split
            int length = to - from;
            Comparable[] aux = new Comparable[length];
            int pAux = 0;
            int pLeft = from;
            int pRight = split;
            while (pLeft < split || pRight < to) {
                if (pLeft < split && pRight < to) {
                    if (array[pLeft].compareTo(array[pRight]) <= 0) {
                        aux[pAux++] = array[pLeft++];
                    } else {
                        aux[pAux++] = array[pRight++];
                    }
                } else if (pLeft < split) {
                    aux[pAux++] = array[pLeft++];
                } else if (pRight < to) {
                    aux[pAux++] = array[pRight++];
                }
            }
            System.arraycopy(aux, 0, array, from, length);
        }

        @Override
        protected void compute() {
            int length = to - from;
            if (length < mSize) {
                computeDirectly(array, from, to);
                return;
            }
            int split = from + (length / 2);
            invokeAll(new MergeAction<>(array, from, split), new MergeAction<>(array, split, to));
            merge(array, from, split, to);
        }
    }
}

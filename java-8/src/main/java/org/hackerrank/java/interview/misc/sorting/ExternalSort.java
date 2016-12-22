package org.hackerrank.java.interview.misc.sorting;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public abstract class ExternalSort {

    public abstract void sort(String source, String target, int memorySize) throws IOException;

    final InternalMergeSort innerSort = new InternalMergeSort();

    protected static class InternalMergeSort {

        public byte[] sort(byte[] array, int length) {
            MergeAction fb = new MergeAction(array, 0, length);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.invoke(fb);
            return array;
        }

        private static class MergeAction extends RecursiveAction {
            private final static int mSize = 8;
            private final byte[] array;
            private final int from;
            private final int to;

            MergeAction(byte[] array, int from, int to) {
                this.array = array;
                this.from = from;
                this.to = to;
            }

            void computeDirectly(byte[] array, int from, int to) {
                Arrays.sort(array, from, to);
            }

            void merge(byte[] array, int from, int split, int to) {

                int length = to - from;
                byte[] aux = new byte[length];
                int pAux = 0;
                int pLeft = from;
                int pRight = split;
                while (pLeft < split || pRight < to) {
                    if (pLeft < split && pRight < to) {
                        if (array[pLeft] <= array[pRight]) {
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
                invokeAll(new MergeAction(array, from, split), new MergeAction(array, split, to));
                merge(array, from, split, to);
            }
        }
    }
}

package org.hackerrank.java.interview.multiplication.table;

import java.util.*;
import java.util.stream.Stream;

class Generator {
    private final int r;
    private final int c;

    Generator(int r, int c) {
        this.r = r;
        this.c = c;
    }

    Stream<IntPair> count(int num) {
        Collection<IntPair> pairs = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            pairs.add(nextUnique());
        }
        return pairs.stream();
    }

    private synchronized IntPair nextUnique() {
        return new IntPair(new Random().nextInt(r), new Random().nextInt(c));
    }

    public static class IntPair {

        private final int r;
        private final int c;

        IntPair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IntPair)) return false;
            IntPair intPair = (IntPair) o;
            return r == intPair.r && c == intPair.c;
        }

        @Override
        public int hashCode() {
            return 31 * r + c;
        }
    }

}

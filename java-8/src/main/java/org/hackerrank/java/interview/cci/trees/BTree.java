package org.hackerrank.java.interview.cci.trees;

public class BTree {
    private final Integer v;
    private final BTree left;
    private final BTree right;

    private BTree(Integer v, BTree left, BTree right) {
        this.v = v;
        this.left = left;
        this.right = right;
    }

    public Integer getV() {
        return v;
    }

    public BTree getLeft() {
        return left;
    }

    public BTree getRight() {
        return right;
    }

    public static BTreeBuilder builder() {
        return new BTreeBuilder();
    }

    public static class BTreeBuilder {
        private Integer v;
        private BTree left;
        private BTree right;

        public BTreeBuilder of(Integer v) {
            this.v = v;
            return this;
        }

        public BTreeBuilder left(BTree left) {
            this.left = left;
            return this;
        }

        public BTreeBuilder right(BTree right) {
            this.right = right;
            return this;
        }

        public BTree build() {
            return new BTree(v, left, right);
        }
    }
}

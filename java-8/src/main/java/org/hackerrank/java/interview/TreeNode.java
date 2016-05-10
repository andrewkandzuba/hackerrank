package org.hackerrank.java.interview;

public class TreeNode<T extends Comparable<? super T>> {
    private T value;
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T value, TreeNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        left.setParent(this);
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        right.setParent(this);
        this.right = right;
    }
}

package org.hackerrank.java.interview.cci.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node<T> {
    private final T value;
    private final Node<T> left;
    private final Node<T> right;

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public boolean isBalanced() {
        return left == null && right == null || Math.abs(height(left) - height(right)) <= 1;
    }

    public void print() {
        List<List<Node<T>>> levels = traverseBFS();
        for (List<Node<T>> level : levels) {
            for (Node<T> n : level) {
                System.out.print(n.value + " ");
            }
            System.out.println("");
        }
    }

    public int height(){
        return this.height(this);
    }

    private int height(Node<T> root) {
        if (root == null) {
            return 0;
        }
        int leftHeight =  1 + height(root.left);
        int rightHeight = 1 + height(root.right);
        return Math.max(leftHeight, rightHeight);
    }

    private List<List<Node<T>>> traverseBFS() {
        Queue<Node<T>> nodes = new LinkedList<>();
        List<List<Node<T>>> levels = new LinkedList<>();

        nodes.add(this);
        while (!nodes.isEmpty()) {
            List<Node<T>> level = new ArrayList<>();
            levels.add(level);

            for (Node<T> node : new ArrayList<>(nodes)) {
                level.add(node);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                nodes.poll();
            }
        }
        return levels;
    }


}

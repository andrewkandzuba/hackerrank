package org.hackerrank.java.interview.cci.trees;

import java.util.*;

class BTree {
    private final int v;
    private BTree left;
    private BTree right;

    private BTree(int v) {
        this.v = v;
    }

    static BTree root(int v) {
        return new BTree(v);
    }

    BTree left(int v) {
        left = new BTree(v);
        return this;
    }

    BTree left(BTree t) {
        left = t;
        return this;
    }

    BTree right(int v) {
        right = new BTree(v);
        return this;
    }

    BTree right(BTree t) {
        right = t;
        return this;
    }

    boolean bst() {
        return dfsMax(this.left, v) <= v && dfsMin(this.right, v) >= v;
    }

    boolean balanced() {
        return left == null && right == null || Math.abs(height(left) - height(right)) <= 1;
    }

    void print() {
        List<List<BTree>> levels = traverseBFS();
        for (List<BTree> level : levels) {
            for (BTree n : level) {
                System.out.print(n.v + " ");
            }
            System.out.println("");
        }
    }

    int height() {
        return this.height(this);
    }

    private int height(BTree root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 1 + height(root.left);
        int rightHeight = 1 + height(root.right);
        return Math.max(leftHeight, rightHeight);
    }

    private List<List<BTree>> traverseBFS() {
        Queue<BTree> nodes = new LinkedList<>();
        List<List<BTree>> levels = new LinkedList<>();
        nodes.add(this);
        while (!nodes.isEmpty()) {
            List<BTree> level = new ArrayList<>();
            levels.add(level);
            for (BTree node : new ArrayList<>(nodes)) {
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

    private int dfsMax(BTree root, int currMax){
        if(root == null){
            return currMax;
        }
        if(root.left != null && root.right != null){
            return Math.max(currMax, Math.max(dfsMax(root.left, currMax), dfsMax(root.right, currMax)));
        } else if (root.left != null) {
            return Math.max(currMax, dfsMax(root.left, currMax));
        } else if (root.right != null){
            return Math.max(currMax, dfsMax(root.right, currMax));
        }
        return Math.max(currMax, root.v);
    }

    private int dfsMin(BTree root, int currMin){
        if(root == null){
            return currMin;
        }
        if(root.left != null && root.right != null){
            return Math.min(currMin, Math.min(dfsMin(root.left, currMin), dfsMin(root.right, currMin)));
        } else if (root.left != null) {
            return Math.min(currMin, dfsMin(root.left, currMin));
        } else if (root.right != null){
            return Math.min(currMin, dfsMin(root.right, currMin));
        }
        return Math.min(currMin, root.v);
    }
}

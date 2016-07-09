package org.hackerrank.java.interview.cci.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    int v() {
        return v;
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

    BTree find(int v) {
        return find(this, v);
    }

    BTree inorderSuccessorOf() {
        return inorderSuccessorOf(this, this.v, null);
    }

    BTree inorderSuccessorOf(int v) {
        return inorderSuccessorOf(this, v, null);
    }

    BTree fistCommonAncestorOf(int a, int b) {
        return fistCommonAncestorOf(this, a, b);
    }

    void printAllPathsOfSum(int sum){
        printAllPathsOfSum(this, sum);
    }

    private void printAllPathsOfSum(BTree r, int sum){
        if(r.left != null){
            printAllPathsOfSum(r.left, sum - r.v);
            printAllPathsOfSum(r.left, sum);
        }
        if(r.right != null){
            printAllPathsOfSum(r.right, sum - r.v);
            printAllPathsOfSum(r.right, sum);
        }
        if(sum - r.v == 0) {
            System.out.println("Found: ->" + r.v);
        }
    }

    private BTree fistCommonAncestorOf(BTree r, int a, int b) {
        if (r == null) {
            return null;
        }
        if (r.v == a || r.v == b) {
            return r;
        }
        BTree rl = fistCommonAncestorOf(r.left, a, b);
        BTree rr = fistCommonAncestorOf(r.right, a, b);
        if (rl != null && rr != null) {
            return r;
        }
        return rl != null ? rl : rr ;
    }

    private BTree inorderSuccessorOf(BTree r, int v, BTree successor) {
        if(r == null){
            return null;
        }
        if (v == r.v) {
            return (r.right != null) ? leftMostOf(r.right) : successor;
        }
        if (v < r.v) {
            return inorderSuccessorOf(r.left, v, r);
        }
        return inorderSuccessorOf(r.right, v, successor);
    }

    private BTree leftMostOf(BTree t) {
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private BTree find(BTree r, int v) {
        if (r == null) {
            return null;
        }
        if (r.v == v) {
            return r;
        }
        if (v < r.v) {
            return (r.left == null) ? null : find(r.left, v);
        }
        return (r.right == null) ? null : find(r.right, v);
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

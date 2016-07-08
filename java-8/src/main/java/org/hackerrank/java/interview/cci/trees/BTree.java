package org.hackerrank.java.interview.cci.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTree {
    private final int v;
    private BTree left;
    private BTree right;

    private BTree(int v) {
        this.v = v;
    }

    public static BTree root(int v){
        return new BTree(v);
    }

    public BTree left(int v){
        left = new BTree(v);
        return this;
    }

    public BTree left(BTree t){
        left = t;
        return this;
    }

    public BTree right(int v){
        right = new BTree(v);
        return this;
    }

    public BTree right(BTree t){
        right = t;
        return this;
    }

    boolean bst() {
        Queue<BTree> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            BTree tree = queue.poll();
            if(tree.left != null && tree.right != null && (tree.left.v < tree.v || tree.left.v > tree.right.v || tree.right.v < tree.v)){
                return false;
            } else if (tree.left != null && tree.left.v < tree.v) {
                return false;
            } if (tree.right != null && tree.right.v < tree.v) {
                return false;
            }
            if(tree.left != null){
                queue.offer(tree.left);
            }
            if(tree.right != null){
                queue.offer(tree.right);
            }
        }
        return true;
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

    int height(){
        return this.height(this);
    }

    private int height(BTree root) {
        if (root == null) {
            return 0;
        }
        int leftHeight =  1 + height(root.left);
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
}

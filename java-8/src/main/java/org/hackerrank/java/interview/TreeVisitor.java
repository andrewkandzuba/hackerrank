package org.hackerrank.java.interview;

public class TreeVisitor<T extends Comparable<? super T>> {
    public void visit(TreeNode<T> node){
        if(node != null){
            System.out.print(node.getValue() + ",");
            visit(node.getLeft());
            visit(node.getRight());
        }
    }
}

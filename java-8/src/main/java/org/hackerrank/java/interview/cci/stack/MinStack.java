package org.hackerrank.java.interview.cci.stack;


public class MinStack<T extends Comparable> {

    private Node<T> top;

    synchronized T pop() {
        if (top != null) {
            T item = top.getData();
            top = top.getNext();
            return item;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    synchronized void push(T item) {
        if (top == null) {
            top = new Node<>(item, null);
            top.setMin(top);
        } else {
            if (top.getMin().getData().compareTo(item) > 0) {
                top = new Node<>(item, top);
                top.setMin(top);
            } else {
                Node<T> min = top.getMin();
                top = new Node<>(item, top);
                top.setMin(min);
            }
        }
    }

    synchronized T min(){
        if(top == null){
            return null;
        }
        if(top.getMin() == null){
            return top.getData();
        }
        return top.getMin().getData();
    }

    static final class Node<T> {
        private final T data;
        private final Node<T> next;
        private Node<T> min;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        T getData() {
            return data;
        }

        Node<T> getMin(){
            return min;
        }

        Node<T> getNext() {
            return next;
        }

        public void setMin(Node<T> min) {
            this.min = min;
        }
    }
}
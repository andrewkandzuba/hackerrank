package org.hackerrank.java.interview;

public class SinglyNode<T> {
    private T data;
    private SinglyNode<T> next;

    public SinglyNode(T d) {
        this.data = d;
    }

    public void appendToTail(T data){
        SinglyNode<T> end = new SinglyNode<>(data);
        SinglyNode<T> n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public T getData() {
        return data;
    }

    public SinglyNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SinglyNode<T> n = this;
        while (n.next != null) {
            if(sb.length() > 0){
                sb.append(",");
            }
            sb.append(n.data.toString());
            n = n.next;
        }
        return "SinglyNode{" + sb.toString() + "}";
    }
}

package org.hackerrank.java.interview;

public class DoublyNode<T extends Comparable<? super T>> {
    private T data;
    private DoublyNode<T> next;
    private DoublyNode<T> prev;

    public DoublyNode(T d) {
        this.data = d;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoublyNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }

    public DoublyNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoublyNode<T> prev) {
        this.prev = prev;
    }

    public void appendToTail(T data){
        DoublyNode<T> end = new DoublyNode<>(data);
        DoublyNode<T> n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
        end.prev = n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoublyNode<T> n = this;
        while (n != null) {
            if(sb.length() > 0){
                sb.append(",");
            }
            sb.append(n.data.toString());
            n = n.next;
        }
        return "DoublyNode{" + sb.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoublyNode)) return false;

        DoublyNode<?> that = (DoublyNode<?>) o;

        return getData() != null ? getData().equals(that.getData()) : that.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getData() != null ? getData().hashCode() : 0;
        result = 31 * result + (getNext() != null ? getNext().hashCode() : 0);
        result = 31 * result + (getPrev() != null ? getPrev().hashCode() : 0);
        return result;
    }
}

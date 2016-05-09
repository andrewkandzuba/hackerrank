package org.hackerrank.java.interview;

import java.util.HashSet;
import java.util.Set;

public class SinglyNode<T extends Comparable<? super T>> {
    private T data;
    private SinglyNode<T> next;
    private boolean visited;

    public SinglyNode(T d) {
        this.data = d;
        this.visited = false;
    }

    public SinglyNode<T> appendToTail(T data) {
        SinglyNode<T> end = new SinglyNode<>(data);
        SinglyNode<T> n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
        return end;
    }

    public T getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public SinglyNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyNode<T> next) {
        this.next = next;
    }


    public <K extends Comparable<T>> void removeDuplicates() {
        SinglyNode<T> current = this;
        while (current != null) {
            SinglyNode<T> runner = current;
            while (runner.getNext() != null) {
                if (current.getData().equals(runner.getNext().getData())) {
                    runner.setNext(runner.getNext().getNext());
                } else {
                    runner = runner.getNext();
                }
            }
            current = current.getNext();
        }
    }

    public <K extends Comparable<T>> void removeDuplicatesWithSet() {
        Set<T> set = new HashSet<>();
        set.add(this.getData());
        SinglyNode<T> runner = this;
        while (runner.getNext() != null) {
            if (set.contains(runner.getNext().getData())) {
                runner.setNext(runner.getNext().getNext());
            } else {
                set.add(runner.getNext().getData());
                runner = runner.getNext();
            }
        }
    }

    public <K extends Comparable<T>> boolean checkNoDuplicates() {
        Set<T> set = new HashSet<>();
        SinglyNode<T> runner = this;
        while (runner != null) {
            if (!set.contains(runner.getData())) {
                set.add(runner.getData());
                runner = runner.getNext();
            } else {
                return false;
            }
        }
        return true;
    }

    public <K extends Comparable<T>> SinglyNode<T> findFromEnd(int k) throws Exception {
        SinglyNode<T> current = this;
        SinglyNode<T> runner = current;
        for (int i = 0; i < k; i++) {
            if (runner.getNext() != null) {
                runner = runner.getNext();
            } else {
                throw new Exception(String.format("%s node from the end not found!!!", k));
            }
        }
        while (runner != null) {
            current = current.getNext();
            runner = runner.getNext();
        }
        return current;
    }

    public static <T extends Comparable<? super T>> SinglyNode<T> deleteInMiddle(SinglyNode<T> start, SinglyNode<T> delete) throws Exception {
        if (delete == null || start == null) {
            return start;
        }
        if (start == delete) {
            return start.getNext();
        }
        SinglyNode<T> current = start;
        SinglyNode<T> runner = start.getNext();
        while (runner != null && !runner.getData().equals(delete.getData())) {
            current = runner;
            runner = runner.getNext();
        }
        if (runner != null) {
            current.setNext(runner.getNext());
        }
        return start;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SinglyNode<T> n = this;
        while (n != null) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(n.data.toString());
            n = n.next;
        }
        return "SinglyNode{" + sb.toString() + "}";
    }
}

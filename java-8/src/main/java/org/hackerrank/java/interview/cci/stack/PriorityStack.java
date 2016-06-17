package org.hackerrank.java.interview.cci.stack;

import java.util.Stack;

public class PriorityStack<T extends Comparable> extends Stack<T> {
    private final Stack<T> exchanger;

    public PriorityStack() {
        this.exchanger = new Stack<T>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T push(T item) {
        synchronized (this) {
            if (this.empty() || super.peek().compareTo(item) <= 0){
                return super.push(item);
            }
            while (!this.empty() && super.peek().compareTo(item) > 0){
                exchanger.push(super.pop());
            }
            T t = super.push(item);
            while (!exchanger.empty()){
                super.push(exchanger.pop());
            }
            return t;
        }
    }
}

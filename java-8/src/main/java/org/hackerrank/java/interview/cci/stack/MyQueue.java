package org.hackerrank.java.interview.cci.stack;

import java.util.Stack;

public class MyQueue<E>  {
    private final Stack<E> inbox = new Stack<E>();
    private final Stack<E> outbox = new Stack<E>();

    public synchronized void enqueue(E e){
        inbox.push(e);
    }

    public synchronized E dequeue(){
        if(outbox.isEmpty()){
            while (!inbox.isEmpty()){
                outbox.push(inbox.pop());
            }
        }
        return outbox.pop();
    }
}

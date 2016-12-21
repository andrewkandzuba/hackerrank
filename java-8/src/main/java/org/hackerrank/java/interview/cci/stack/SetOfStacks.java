package org.hackerrank.java.interview.cci.stack;

import java.security.InvalidParameterException;
import java.util.Stack;

public class SetOfStacks<E> {
    private final int maxStackDeep;
    private final Stack<Stack<E>> stacks;

    public SetOfStacks(int maxStackDeep) {
        this.maxStackDeep = maxStackDeep;
        this.stacks = new Stack<>();
    }

    public synchronized void push(E e) {
        if (stacks.empty() || stacks.peek().size() == maxStackDeep) {
            stacks.push(new Stack<E>());
        }
        stacks.peek().push(e);
    }

    public synchronized E popAt(int index){
        if(index < 0 || index >= stacks.size()){
            throw new InvalidParameterException("No such sub-stack");
        }
        return stacks.elementAt(index).pop();
    }

    public synchronized E pop() {
        if (stacks.empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E e = stacks.peek().pop();
        if(stacks.peek().empty()){
            stacks.pop();
        }
        return e;
    }
}

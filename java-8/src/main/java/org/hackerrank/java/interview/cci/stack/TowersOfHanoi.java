package org.hackerrank.java.interview.cci.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TowersOfHanoi {
    private final List<Stack<Integer>> towers;

    public TowersOfHanoi(int discs) {
        if (discs <= 0) {
            throw new IllegalStateException("Number of disc should be positive");
        }
        List<Stack<Integer>> stacks = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            stacks.add(new Stack<>());
        }
        for (int d = discs; d >= 0; d--) {
            stacks.get(0).push(d);
        }
        this.towers = Collections.unmodifiableList(stacks);
    }

    public List<Stack<Integer>> getTowers() {
        return towers;
    }

    public void solve() {
        move(towers.get(0).size(), 0, 2);
    }

    /**
     * https://en.wikipedia.org/wiki/Tower_of_Hanoi
     * @param n
     * @param from
     * @param to
     */
    private void move(int n, int from, int to) {
        if (n == 1) {
            towers.get(to).push(towers.get(from).pop());
        } else {
            move(n - 1, 0, 1);
            move(1, 0, 2);
            move(n - 1, 2, 0);
        }
    }
}

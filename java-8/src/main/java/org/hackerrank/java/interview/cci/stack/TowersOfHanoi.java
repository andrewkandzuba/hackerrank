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
        move(towers.get(0).size(), 0, 1, 2);
    }


    /**
     * https://en.wikipedia.org/wiki/Tower_of_Hanoi
     */
    private void move(int disk, int source, int spare, int dest) {
        if (disk == 1) {
            towers.get(dest).push(towers.get(source).pop()); // move disk from source to dest
        } else {
            move(disk - 1, source, dest, spare);   // Step 1 above
            towers.get(dest).push(towers.get(source).pop());  // Step 2 above
            move(disk - 1, spare, source, dest);   // Step 3 above
        }
    }
}

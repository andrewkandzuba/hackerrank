package org.hackerrank.java.interview.cci.graph;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Node {
    private final int id;
    private final List<Node> targets;
    private volatile boolean visited;

    public Node(int id) {
        this.visited = false;
        this.id = id;
        this.targets = new CopyOnWriteArrayList<>();
    }

    public void addTarget(Node n){
        targets.add(n);
    }
}

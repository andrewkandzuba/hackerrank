package org.hackerrank.java.interview.cci.graph;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Node {
    private final int id;
    private final List<Node> targets;
    private volatile boolean visited;

    public Node(int id) {
        this.id = id;
        this.targets = new CopyOnWriteArrayList<>();
        this.visited = false;
    }

    public int getId() {
        return id;
    }

    public void add(Collection<Node> targets){
        targets.addAll(targets);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


}

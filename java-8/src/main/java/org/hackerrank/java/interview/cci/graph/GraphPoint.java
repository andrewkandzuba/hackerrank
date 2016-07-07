package org.hackerrank.java.interview.cci.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphPoint {
    private final int id;
    private final List<GraphPoint> targets;

    public GraphPoint(int id) {
        this.id = id;
        this.targets = new ArrayList<>();
    }

    public void add(GraphPoint... graphPoints){
        targets.addAll(Arrays.asList(graphPoints));
    }

    public List<GraphPoint> targets(){
        return Collections.unmodifiableList(targets);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GraphPoint) {
            GraphPoint other = (GraphPoint) obj;
            return other.id == id;
        }
        return super.equals(obj);
    }
}

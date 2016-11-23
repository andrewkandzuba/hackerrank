package org.hackerrank.java.interview.cci.graph;

import java.util.*;

public class GraphUtils {
    public static boolean pathExists(GraphPoint start, GraphPoint dest){
        Set<GraphPoint> visited = new HashSet<>();
        Queue<GraphPoint> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()){
            GraphPoint gp = queue.poll();
            if(gp.equals(dest)){
                return true;
            }
            gp.targets().stream().filter(o -> !visited.contains(o)).forEach(graphPoint -> {
                visited.add(graphPoint);
                queue.offer(graphPoint);
            });
        }
        return false;
    }

}

package org.hackerrank.java.interview.zillow;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenTreeTests {


    @Test
    public void evenTreeTest() {
        Assert.assertEquals(2, evenForest(10, 9, List.of(2,3,4,5,6,7,8,9,10), List.of(1,1,3,2,1,2,6,8,8)));
    }
    
    static class Node {
        final int node;
        final List<Node> adj = new ArrayList<>();

        Node(int node){
            this.node = node;
        }
    }

    // Complete the evenForest function below.
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {

        boolean [] visited = new boolean[t_nodes+1];
        Map<Integer, Node> graph = new HashMap<>();
        for(int i = 0; i < t_edges; i++) {
            int i_from = t_from.get(i);
            int i_to = t_to.get(i);
            Node u = graph.computeIfAbsent(i_from, idx -> new Node(i_from));
            Node v = graph.computeIfAbsent(i_to, idx -> new Node(i_to));
            u.adj.add(v);
            v.adj.add(u);
        }

        int[] cuts = new int[1];
        Node root = graph.get(1);

        dfs(root, null, visited, cuts);
        return cuts[0];
    }

    private static int dfs(Node root, Node parent, final boolean [] visited, final int[] cuts) {

        int sz = 1;

        visited[root.node] = true;

        for(Node n : root.adj) {
            if(!visited[n.node]) {
                visited[n.node] = true;
                sz += dfs(n, root, visited, cuts);
            }
        }
        if (sz % 2 == 0 && parent != null) {
            cuts[0]++;
        }
        return sz;
    }
}

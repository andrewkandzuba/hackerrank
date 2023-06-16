package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SolutionGrabCount {

    @Test
    public void testCrabCount() {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(1, 4));
        edges.add(List.of(2, 4));
        edges.add(List.of(3, 4));
        edges.add(List.of(5, 4));
        edges.add(List.of(5, 8));
        edges.add(List.of(5, 7));
        edges.add(List.of(5, 6));
        Assert.assertEquals(6, crabGraphs(8, 2, edges));


        edges = new ArrayList<>();
        edges.add(List.of(1, 2));
        edges.add(List.of(2, 3));
        edges.add(List.of(3, 4));
        edges.add(List.of(4, 5));
        edges.add(List.of(5, 6));
        edges.add(List.of(6, 1));
        edges.add(List.of(1, 4));
        edges.add(List.of(2, 5));
        Assert.assertEquals(6, crabGraphs(6, 3, edges));
    }

    private static List<Node> leaves = new ArrayList<>();
    private static List<Node> heads = new ArrayList<>();

    public static int crabGraphs(int n, int t, List<List<Integer>> graph) {
        // Write your code here

        List<Set<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacency.add(new HashSet<>());
        }
        for (List<Integer> edge : graph) {
            int n1 = edge.get(0) - 1;
            int n2 = edge.get(1) - 1;
            adjacency.get(n1).add(n2);
            adjacency.get(n2).add(n1);
        }

        Deque<Integer> leaves = new ArrayDeque<>();
        int cover = n;
        for (int i = 0; i < n; i++) {
            if (adjacency.get(i).isEmpty()) {
                cover--;
            } else if (adjacency.get(i).size() == 1) {
                leaves.addLast(i);
            }
        }

        int[] legs = new int[n];

        while (!leaves.isEmpty()) {
            int leaf = leaves.removeFirst();
            if (legs[leaf] > 0) {
                continue;
            }

            if (adjacency.get(leaf).isEmpty()) {
                cover--;
            } else {
                int head = adjacency.get(leaf).stream().findAny().get();
                legs[head]++;
                if (legs[head] == t) {
                    for (int neighbor : adjacency.get(head)) {
                        adjacency.get(neighbor).remove(head);
                        if (adjacency.get(neighbor).size() == 1) {
                            leaves.addLast(neighbor);
                        }
                    }
                }
            }
        }
        return cover;

    }

    private static class Node implements Comparable<Node> {
        final int value;
        final Set<Node> adjacent = new HashSet<>();
        boolean visited;
        boolean head;

        Node(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node n) {
            return this.value - n.value;
        }
    }
}

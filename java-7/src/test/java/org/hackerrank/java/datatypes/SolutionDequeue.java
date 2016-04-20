package org.hackerrank.java.datatypes;

import java.util.*;

public class SolutionDequeue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> array = new ArrayList<>();
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            deque.addLast(in.nextInt());
        }
        int maxUnique = 0;
        for (int i = 0; i < n - m + 1; i++) {
            Set<Integer> un = new HashSet<>();
            for(int j=0; j < m; j++){
            }
        }
        System.out.println(maxUnique);
    }
}

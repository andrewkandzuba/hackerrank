package org.hackerrank.java.datatypes;

import java.util.*;

public class SolutionDequeue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int maxUniqueCount = 0;
        int currUnique = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.addLast(num);
            Integer count = map.get(num);
            if (count == null) {
                currUnique++;
                map.put(num, 1);
            } else {
                map.put(num, count + 1);
            }
            if (deque.size() == m) {
                maxUniqueCount = Math.max(maxUniqueCount, currUnique);
                int removed = deque.pollFirst();
                count = map.get(removed);
                if (count != null) {
                    if (count == 1) {
                        map.remove(removed);
                        currUnique--;
                    } else {
                        map.put(removed, count - 1);
                    }
                }
            }
        }
        System.out.println(maxUniqueCount);
    }
}

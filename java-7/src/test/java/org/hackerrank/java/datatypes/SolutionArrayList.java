package org.hackerrank.java.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionArrayList {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        List<List<Integer>> lines = new ArrayList<>(n);
        while (n-- > 0) {
            int d = in.nextInt();
            List<Integer> line = new ArrayList<>(d);
            while (d-- > 0) {
                line.add(in.nextInt());
            }
            lines.add(line);
        }
        int q = in.nextInt();
        while (q-- > 0) {
            int y = in.nextInt();
            int x = in.nextInt();
            if (lines.size() < y || lines.get(y - 1).size() < x) {
                System.out.println("ERROR!");
                continue;
            }
            System.out.println(lines.get(y - 1).get(x - 1));
        }
    }
}

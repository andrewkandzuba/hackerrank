package org.hackerrank.java;

import java.util.Scanner;

public class SolutionEOF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lineOrder = 0;
        while (sc.hasNextLine()) {
            System.out.printf("%d %s%n", ++lineOrder, sc.nextLine());
        }
        sc.close();
    }
}

package org.hackerrank.java.intro;

import java.util.Scanner;

public class SolutionJavaLoops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testsCount = sc.nextInt();
        for (int i = 0; i < testsCount; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int n = sc.nextInt();
            int sum = a;
            for (int t = 0; t < n; t++) {
                sum += Math.pow(2, t) * b;
                System.out.printf("%s ", sum);
            }
            System.out.printf("%n");
        }
    }
}

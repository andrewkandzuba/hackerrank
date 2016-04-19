package org.hackerrank.java;

import java.util.Scanner;

public class SolutionArrayGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] array = new int[n];
            while (n > 0) {
                array[array.length - n--] = in.nextInt();
            }
            // ready for game
        }
    }
}

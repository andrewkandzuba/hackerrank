package org.hackerrank.java.datatypes;

import java.util.Scanner;

public class SolutionArrayGame {
    private static boolean play(int[] array, int cp, int m) {
        if (cp + 1 > array.length - 1 || cp + m > array.length - 1) {
            return true;
        }
        boolean res = false;
        if (array[cp + 1] == 0) {
            array[cp + 1] = 1;
            res = play(array, cp + 1, m);
        }
        if (cp > 0 && array[cp - 1] == 0) {
            array[cp - 1] = 1;
            res |= play(array, cp - 1, m);
        }
        if (array[cp + m] == 0) {
            array[cp + m] = 1;
            res |= play(array, cp + m, m);
        }
        return res;
    }

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
            testCases--;
            boolean res = play(array, 0, m);
            System.out.println(res ? "YES" : "NO");
        }
    }
}

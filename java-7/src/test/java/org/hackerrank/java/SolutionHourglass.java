package org.hackerrank.java;

import java.util.Scanner;

public class SolutionHourglass {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int maxHourGlassSum = -9 * 7;
        for (int layer = 1; layer < arr.length / 2; layer++) {
            for (int row = layer; row < arr.length - layer; row++) {
                for (int col = layer; col < arr[row].length - layer; col++) {
                    int hourGlassSum =
                            arr[row - 1][col - 1] + arr[row - 1][col] + arr[row - 1][col + 1] +
                                                arr[row][col] +
                            arr[row + 1][col - 1] + arr[row + 1][col] + arr[row + 1][col + 1];
                    maxHourGlassSum = Math.max(maxHourGlassSum, hourGlassSum);
                }
            }
        }
        System.out.print(maxHourGlassSum);

        /*for (int[] anArr : arr) {
            for (int anAnArr : anArr) {
                System.out.printf("%2d", anAnArr);
            }
            System.out.println();
        }*/
    }
}

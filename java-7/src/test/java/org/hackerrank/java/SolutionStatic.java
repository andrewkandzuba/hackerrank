package org.hackerrank.java;


import java.util.Scanner;

public class SolutionStatic {
    private static boolean flag;
    private static byte B;
    private static byte H;

    static {
        try {
            Scanner sc = new Scanner(System.in);
            B = sc.nextByte();
            H = sc.nextByte();
            if (B < 0) {
                throw new Exception("Breadth and height must be positive");
            }
            if (H < 0) {
                throw new Exception("Breadth and height must be positive");
            }
            flag = true;
        } catch (Exception e) {
            System.out.printf("%s: %s%n", Exception.class.getName(), e.getMessage());
            flag = false;
        }
    }

    public static void main(String[] args) {
        if (flag) {
            int area = B * H;
            System.out.print(area);
        }

    }//end of main

}//end of class

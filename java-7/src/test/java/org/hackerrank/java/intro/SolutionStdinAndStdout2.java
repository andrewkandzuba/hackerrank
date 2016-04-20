package org.hackerrank.java.intro;

import java.util.Scanner;

public class SolutionStdinAndStdout2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        double y = sc.nextDouble();
        String s = sc.next() + sc.nextLine();
        sc.close();

        //Complete this code
        System.out.println("String: " + s);
        System.out.println("Double: " + y);
        System.out.println("Int: " + x);
    }
}

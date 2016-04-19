package org.hackerrank.java;

import java.util.Scanner;

public class SolutionPalindrome {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        boolean isPalindrome = true;
        if (A.length() > 1) {
            for (int i = 0; i < A.length() / 2; i++) {
                if (A.charAt(i) != A.charAt(A.length() - i - 1)){
                    isPalindrome = false;
                    break;
                }
            }
        }
        System.out.println(isPalindrome ? "Yes" : "No");
    }
}

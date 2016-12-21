package org.hackerrank.java.strings;

import java.util.Scanner;

public class SolutionStringCompare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        if(k < 0 || k > s.length()) {
            System.out.println("Substring length invalid");
        } else {
            String maxS = s.substring(0, k);
            String minS = s.substring(0, k);
            for(int i = 1; i < s.length() - k + 1; i++){
                String subString = s.substring(i, i + k);
                if(subString.compareTo(minS) < 0) minS = subString;
                if(subString.compareTo(maxS) > 0) maxS = subString;
            }
            System.out.println(minS);
            System.out.println(maxS);
        }
    }
}

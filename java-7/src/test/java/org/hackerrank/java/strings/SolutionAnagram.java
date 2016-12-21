package org.hackerrank.java.strings;

import java.util.Scanner;

public class SolutionAnagram {
    static boolean isAnagram(String A, String B) {
        int[] charFreqA = new int[Character.MAX_VALUE + 1];
        int[] charFreqB = new int[Character.MAX_VALUE + 1];
        for(char c : A.toLowerCase().toCharArray()) charFreqA[(int)c]++;
        for(char c : B.toLowerCase().toCharArray()) charFreqB[(int)c]++;
        for(int i = 0; i < Character.MAX_VALUE + 1; i++){
            if(charFreqA[i] != charFreqB[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        String B=sc.next();
        boolean ret=isAnagram(A,B);
        if(ret)System.out.println("Anagrams");
        else System.out.println("Not Anagrams");
    }
}

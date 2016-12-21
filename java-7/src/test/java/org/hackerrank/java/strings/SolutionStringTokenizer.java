package org.hackerrank.java.strings;

import java.util.Scanner;

public class SolutionStringTokenizer {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if(s.trim().length() == 0){
            System.out.println(0);
        } else {
            String[] words = s.trim().split("[^A-Za-z]+");
            System.out.println(words.length);
            for (String word : words) {
                System.out.println(word);
            }
        }
    }
}

package org.hackerrank.java.datatypes;

import java.util.Scanner;

public class SolutionSubArrays {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        while (n > 0) {
            array[array.length - n--] = in.nextInt();
        }
        int negativeCount = 0;
        for(int length = 1; length <= array.length; length++){
            for(int offset = 0; offset <= array.length - length; offset++){
                int subArraySum = 0;
                for(int index = offset; index < offset + length; index++){
                    subArraySum += array[index];
                }
                if(subArraySum < 0){
                    negativeCount++;
                }
            }
        }
        System.out.println(negativeCount);
    }
}

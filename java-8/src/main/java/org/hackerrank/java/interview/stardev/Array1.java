package org.hackerrank.java.interview.stardev;

public class Array1 {

    public static int[] rotateLeft3(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        return array;
    }
}

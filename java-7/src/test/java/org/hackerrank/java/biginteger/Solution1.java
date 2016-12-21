package org.hackerrank.java.biginteger;

import java.math.BigInteger;
import java.util.Scanner;

public class Solution1 {

    public static void main(String ...args){
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();

        System.out.printf("%s%n", new BigInteger(a).add(new BigInteger(b)).toString());
        System.out.printf("%s%n", new BigInteger(a).multiply(new BigInteger(b)).toString());
    }
}

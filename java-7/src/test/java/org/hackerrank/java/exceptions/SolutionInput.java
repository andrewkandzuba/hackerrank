package org.hackerrank.java.exceptions;


import java.util.Scanner;

public class SolutionInput {

    public static void main(String[] args) {
     /*   Scanner in = new Scanner(System.in);
        try {
            int dividend = Integer.parseInt(in.nextLine());
            int divisor = Integer.parseInt(in.nextLine());
            System.out.printf("%d%n", dividend / divisor);
        } catch (NumberFormatException e){
            System.out.println("java.util.InputMismatchException");
        } catch (ArithmeticException e){
            System.out.println("java.lang.ArithmeticException: / by zero");
        }*/

        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        double y=sc.nextDouble();
        sc.nextLine();
        String s = sc.nextLine();

        System.out.println("String: "+s);
        System.out.println("Double: "+y);
        System.out.println("Int: "+x);
    }
}

package org.hackerrank.java.interview.stardev;

public class Recursion {

    public static int factorial(int n) {
        if (n == 1) {
            return n;
        }
        return n * factorial(n - 1);
    }

    public static int bunnyEars(int bunnies) {
        if (bunnies == 0) {
            return 0;
        }
        return 2 + bunnyEars(bunnies - 1);
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int bunnyEars2(int n) {
        if (n == 0) {
            return 0;
        }
        return ((n % 2 > 0) ? 2 : 3) + bunnyEars2(n - 1);
    }

    public static int triangle(int rows) {
        if (rows == 0) {
            return 0;
        }
        return rows + triangle(rows - 1);
    }

    public static int sumDigits(int n) {
        if (n / 10 == 0) {
            return n;
        }
        return n % 10 + sumDigits(n / 10);
    }

    public static int count7(int n) {
        if (n == 0) {
            return 0;
        }
        return ((n % 10 == 7) ? 1 : 0) + count7(n / 10);
    }

}

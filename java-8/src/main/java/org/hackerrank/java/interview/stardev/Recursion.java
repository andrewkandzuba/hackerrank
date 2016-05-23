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

    public static int count8(int n) {
        if (n == 0) {
            return 0;
        }

        int left = n / 10;
        boolean is8 = n % 10 == 8;
        boolean isLeft8 = (left % 10) == 8;

        return (isLeft8 ? 2 : 1) * (is8 ? 1 : 0) + count8(left);
    }

    public static int powerN(int base, int n) {
        if (base == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        return base * powerN(base, n - 1);
    }

    public static int countX(String str) {
        return countX(0, str.toCharArray());
    }

    private static int countX(int start, char[] array) {
        if (start >= array.length) {
            return 0;
        }
        return (array[start] == 'x' ? 1 : 0) + countX(start + 1, array);
    }

    public static int countHi(String str) {
        return countHi(0, str.toCharArray());
    }

    private static int countHi(int start, char[] array) {
        if (start >= array.length) {
            return 0;
        }
        if (array[start] == 'h' && start + 1 < array.length && array[start + 1] == 'i') {
            return 1 + countHi(start + 2, array);
        }
        return countHi(start + 1, array);
    }

    public static String changeXY(String str) {
        if (str.length() == 0) {
            return "";
        }
        return (str.charAt(0) == 'x' ? "y" : str.substring(0, 1)) + changeXY(str.substring(1));
    }

    public static int strDist(String str, String sub) {
        return strDist(str, sub, 0);
    }

    private static int strDist(String str, String sub, int accumulated) {
        if (sub.length() > str.length()) {
            return accumulated;
        }
        int subStart = str.indexOf(sub);
        if (str.startsWith(sub)) {
            return strDist(
                    str.substring(subStart + sub.length()),
                    sub,
                    accumulated + subStart + sub.length());
        } else {
            return strDist(str.substring(1), sub, accumulated);
        }
    }
}

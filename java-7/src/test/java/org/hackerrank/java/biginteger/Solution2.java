package org.hackerrank.java.biginteger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Solution2 {

    public static void main(String[] argh) {
        //Input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        //Write your code here
        mergeSortRecursive(s, 0, s.length);
        //Output
        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }
    }

    private static void merge(String[] a, int left, int mid, int right) {
        int it1 = 0;
        int it2 = 0;
        String[] result = new String[right - left];

        while (left + it1 < mid && mid + it2 < right) {

            BigDecimal a1 = new BigDecimal(a[left + it1]);
            BigDecimal a2 = new BigDecimal(a[mid + it2]);
            int maxScale = Math.max(a1.scale(), a2.scale());

            if (a2.setScale(maxScale, RoundingMode.HALF_UP).compareTo(a1.setScale(maxScale, RoundingMode.HALF_UP)) <= 0) {
                result[it1 + it2] = a[left + it1];
                it1++;
            } else {
                result[it1 + it2] = a[mid + it2];
                it2++;
            }
        }
        while (left + it1 < mid) {
            result[it1 + it2] = a[left + it1];
            it1++;
        }
        while (mid + it2 < right) {
            result[it1 + it2] = a[mid + it2];
            it2++;
        }
        System.arraycopy(result, 0, a, left, it1 + it2);

    }

    private static void mergeSortRecursive(String[] a, int left, int right) {
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortRecursive(a, left, mid);
        mergeSortRecursive(a, mid, right);
        merge(a, left, mid, right);
    }

}

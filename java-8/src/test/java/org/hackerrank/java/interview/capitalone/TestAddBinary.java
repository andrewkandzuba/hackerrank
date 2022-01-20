package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestAddBinary {

    @Test
    public void test1() {
        Assert.assertEquals("100", new Solution().addBinary("11", "1"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("0", new Solution().addBinary("0", "0"));
    }

    @Test
    public void test3() {
        Assert.assertEquals("10101", new Solution().addBinary("1010", "1011"));
    }

    @Test
    public void test4() {
        Assert.assertEquals("11110", new Solution().addBinary("1111", "1111"));
    }

    @Test
    public void name() {
        int day = 31, month = 8, year = 2019;
    }

    class Solution {
        public String addBinary(String a, String b) {

            StringBuilder result = new StringBuilder();

            char reminder = '0';
            int p1 = a.length() - 1;
            int p2 = b.length() - 1;

            while (p1 >= 0 || p2 >= 0) {
                char c1 = (p1 >= 0) ? a.charAt(p1--) : '0';
                char c2 = (p2 >= 0) ? b.charAt(p2--) : '0';

                if (c1 == '1' && c2 == '1') {
                    if (reminder == '1') {
                        result.insert(0, '1');
                    } else {
                        result.insert(0, '0');
                        reminder = '1';
                    }
                } else if (c1 == '1' || c2 == '1') {
                    if (reminder == '0') {
                        result.insert(0, '1');
                    } else {
                        result.insert(0, '0');
                    }
                } else {
                    if (reminder == '1') {
                        result.insert(0, '1');
                        reminder = '0';
                    } else {
                        result.insert(0, '0');
                    }
                }
            }

            if (reminder == '1') {
                result.insert(0, '1');
            }
            if (result.length() == 0) {
                result.append('0');
            }

            return result.toString();
        }

    }
}

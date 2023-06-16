package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestLongestCommonPrefix {
    @Test
    public void test1() {
        Assert.assertEquals("fl", new Solution().longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    @Test
    public void test2() {
        Assert.assertEquals("", new Solution().longestCommonPrefix(new String[]{"","",""}));
    }

    @Test
    public void test3() {
        Assert.assertEquals("aaaaa", new Solution().longestCommonPrefix(new String[]{"aaaaa"}));
    }

    @Test
    public void test4() {
        Assert.assertEquals("", new Solution().longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

    private class Solution {
        public String longestCommonPrefix(String[] strs) {

            int l = 0;
            for (int i = 0; i < strs.length; i++) {
                if (i == 0) {
                    l = strs[i].length();
                    continue;
                }
                l = min(l, strs[i].length());
            }

            if (l == 0) {
                return "";
            }

            for (int p = 0; p < l; p++) {
                char prev = 0x0;
                for (int i = 0; i < strs.length; i++) {
                    if (i == 0) {
                        prev = strs[i].charAt(p);
                        continue;
                    }
                    if (prev != strs[i].charAt(p)) {
                        return strs[i].substring(0, p);
                    }
                }
            }
            return strs[0].substring(0, l);
        }

        private int min(int a, int b) {
            return (a < b) ? a : b;
        }
    }
}

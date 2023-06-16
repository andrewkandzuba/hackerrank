package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestSequenceOfStringWithOneCharDiff {

    private class SequenceOfStringWithOneCharDiff {

        boolean solution(String[] inputArray) {
            int count = 0;
            for(int i = 0; i < inputArray.length - 1; i++) {
                for (int j = i + 1; j < inputArray.length; j++) {
                    if(isDiffByOneChar(inputArray[i], inputArray[j])) {
                        count++;
                    }
                }
            }
            return count >= inputArray.length - 1;
        }

        private boolean isDiffByOneChar(String s1, String s2) {
            int count = 0;
            for(int i = 0; i < s1.length(); i++) {
                if(s1.charAt(i) != s2.charAt(i)){
                    count++;
                }
            }
            return count == 1;
        }
    }

    @Test
    public void test1() {
        Assert.assertTrue(new SequenceOfStringWithOneCharDiff().solution(new String[]{"aa", "bb", "ab"}));
    }

    @Test
    public void test2() {
        Assert.assertFalse(new SequenceOfStringWithOneCharDiff().solution(new String[]{"aba",
                "bbb",
                "bab"}));
    }

    @Test
    public void test3() {
        Assert.assertFalse(new SequenceOfStringWithOneCharDiff().solution(new String[]{"q", "q"}));
    }

    @Test
    public void test4() {
        Assert.assertTrue(new SequenceOfStringWithOneCharDiff().solution(new String[]{"zzzzab",
                "zzzzbb",
                "zzzzaa"}));
    }

    @Test
    public void test6() {
        Assert.assertTrue(new SequenceOfStringWithOneCharDiff().solution(new String[]{"abc",
                "bef",
                "bcc",
                "bec",
                "bbc",
                "bdc"}));
    }

    @Test
    public void test10() {
        Assert.assertFalse(new SequenceOfStringWithOneCharDiff().solution(new String[]{"abc",
                "abx",
                "axx",
                "abc"}));
    }

}

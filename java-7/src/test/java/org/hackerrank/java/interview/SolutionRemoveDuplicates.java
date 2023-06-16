package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SolutionRemoveDuplicates {

    @Test
    public void testRemoveDuplicate() {
        Assert.assertEquals("aa", removeDuplicates("deeedbbcccbdaa", 3));
        Assert.assertEquals("ps", removeDuplicates("pbbcggttciiippooaais", 2));
        Assert.assertEquals("abcd", removeDuplicates("abcd", 2));
    }

    public String removeDuplicates(String s, int k) {

        Deque<CharNode> deq = new LinkedList<>();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (deq.isEmpty()) {
                deq.push(new CharNode(s.charAt(i++)));
                continue;
            }
            CharNode cn = deq.peek();
            if (c == cn.c) {
                cn.count++;
                if (cn.count == k) {
                    deq.pop();
                }
            } else {
                deq.push(new CharNode(s.charAt(i)));
            }
            i++;
        }

        StringBuilder sb = new StringBuilder();
        while (!deq.isEmpty()) {
            CharNode cn = deq.removeLast();
            sb.append(String.valueOf(cn.c).repeat(Math.max(0, cn.count)));
        }
        return sb.toString();
    }

    private static class CharNode {
        final char c;
        int count = 1;

        public CharNode(char c) {
            this.c = c;
        }
    }
}

package org.hackerrank.java.interview.zillow;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

public class WeeklyPrepDayFourTests {

    @Test
    public void superDigitTest() {

        Assert.assertEquals(3, superDigit("148", 3));
    }

    public static int superDigit(String n, int k) {
        // Write your code here

        int x = calcSuperDigit(n);
        StringBuilder sb = new StringBuilder();
        while (k-->0) {
            sb.append(x);
        }
        x = calcSuperDigit(sb.toString());
        return x;
    }

    private static int calcSuperDigit(String s) {
        int x = 0;
        while (s.length() > 1) {
            int v = 0;
            for(char c : s.toCharArray()){
                v += c - (byte)'0';
            }
            s  = Integer.toString(v);
            x = v;
        }
        return x;
    }

    @Test
    public void bribesTest() {
        minimumBribes(List.of(2, 1, 5, 3, 4));
        minimumBribes(List.of(2, 5, 1, 3, 4));
    }

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int swap = 0;
        int bribes;
        int pos = 0;
        for(int i = q.size() - 1; i >= 0; i--) {
            int j = 0;
            bribes = q.get(pos) - pos - 1;
            if (bribes > 2) {
                System.out.println("Too chaotic");
                return;
            }
            if (q.get(i) - 2 > 0){
                j = q.get(i) - 2 - 1;
            }
            while(j <= i) {
                if (q.get(j) > q.get(i)){
                    swap++;
                }
                j++;
            }
            pos++;
        }
        System.out.println(swap);
    }

    @Test
    public void mergeLists() {
        SinglyLinkedListNode merged = mergeLists(
                new SinglyLinkedListNode(4,
                        new SinglyLinkedListNode(5,
                                new SinglyLinkedListNode(6))),
                new SinglyLinkedListNode(1,
                        new SinglyLinkedListNode(2,
                                new SinglyLinkedListNode(10))));
    }

    private static class SinglyLinkedListNode {
         int data;
         SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }

        public SinglyLinkedListNode(int data, SinglyLinkedListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode mergedHead;
        if(head1.data <= head2.data) {
            SinglyLinkedListNode next = head1.next;
            mergedHead = head1;
            mergedHead.next = null;
            head1 = next;
        } else {
            SinglyLinkedListNode next = head2.next;
            mergedHead = head2;
            mergedHead.next = null;
            head2 = next;
        }
        SinglyLinkedListNode mergedTail = mergedHead;

        while(true) {
            if(head1 == null && head2 == null){
                break;
            }
            if(head1 == null) {
                mergedTail.next = head2;
                break;
            } else if(head2 == null){
                mergedTail.next = head1;
                break;
            }

            if(head1.data <= head2.data) {
                SinglyLinkedListNode next = head1.next;
                mergedTail = append(mergedTail, head1);
                head1 = next;
            } else {
                SinglyLinkedListNode next = head2.next;
                mergedTail = append(mergedTail, head2);
                head2 = next;
            }
        }

        return mergedHead;
    }

    private static SinglyLinkedListNode append(SinglyLinkedListNode tail, SinglyLinkedListNode n) {
        tail.next = n;
        tail = n;
        tail.next = null;
        return tail;
    }

    class Queue {
        private Stack<Integer> s1 = new Stack<>();
        private Stack<Integer> s2 = new Stack<>();

        public void enqueue(int e){
            s1.add(e);
        }

    }

}


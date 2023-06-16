package org.hackerrank.java.interview;

import org.junit.Test;

public class SolutionMergeK {

    @Test
    public void testMergeK() {
        ListNode merged = merge(
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(4)))),
        new ListNode(2, new ListNode(5)));
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    ListNode merge(ListNode head1, ListNode head2){
        ListNode head = null;
        ListNode tail = null;

        ListNode p1 = head1;
        ListNode p2 = head2;

        while(p1 != null && p2 != null){
            ListNode n;
            if(p1.val <= p2.val){
                n = p1;
                p1 = p1.next;
            } else {
                n = p2;
                p2 = p2.next;
            }
            n.next = null;
            if(head == null){
                head = n;
                tail = n;
            } else {
                tail.next = n;
                tail = n;
            }

        }

        if(p1 != null){
            if(tail != null) {
                tail.next = p1;
            } else {
                head = p1;
            }
        } else if(p2!=null){
            if(tail != null) {
                tail.next = p2;
            } else {
                head = p2;
            }
        }

        return head;

    }
}

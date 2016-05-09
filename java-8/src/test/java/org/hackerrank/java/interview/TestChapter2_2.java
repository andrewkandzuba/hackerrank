package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Questions: 2.5(a), 2.5(b)
 */
public class TestChapter2_2 {

    @Test
    public void TestQuestion2_5_a() throws Exception {
        SinglyNode<Integer> n1 = new SinglyNode<>(7);
        n1.appendToTail(1);
        n1.appendToTail(6);

        SinglyNode<Integer> n2 = new SinglyNode<>(5);
        n2.appendToTail(9);
        n2.appendToTail(2);

        SinglyNode<Integer> n = sumReverse(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 2);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 9);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sumReverse(null, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 5);
        Assert.assertEquals(n.getNext().getData().intValue(), 9);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 2);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sumReverse(n1, null);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 7);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 6);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sumReverse(null, null);
        Assert.assertNull(n);

        // Test different lengths
        n1 = new SinglyNode<>(7);
        n1.appendToTail(1);

        n2 = new SinglyNode<>(5);
        n2.appendToTail(9);
        n2.appendToTail(2);

        n = sumReverse(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 2);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 3);
        Assert.assertNull(n.getNext().getNext().getNext());

        n1 = new SinglyNode<>(7);
        n1.appendToTail(1);
        n1.appendToTail(6);

        n2 = new SinglyNode<>(5);
        n2.appendToTail(9);

        n = sumReverse(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 2);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 7);
        Assert.assertNull(n.getNext().getNext().getNext());

        // Make result longer
        n1 = new SinglyNode<>(9);
        n1.appendToTail(9);
        n1.appendToTail(9);

        n2 = new SinglyNode<>(9);
        n2.appendToTail(9);

        n = sumReverse(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 8);
        Assert.assertEquals(n.getNext().getData().intValue(), 9);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 0);
        Assert.assertEquals(n.getNext().getNext().getNext().getData().intValue(), 1);
        Assert.assertNull(n.getNext().getNext().getNext().getNext());
    }


    @Test
    public void TestQuestion2_5_b() throws Exception {
        SinglyNode<Integer> n1 = new SinglyNode<>(6);
        n1.appendToTail(1);
        n1.appendToTail(7);

        SinglyNode<Integer> n2 = new SinglyNode<>(2);
        n2.appendToTail(9);
        n2.appendToTail(5);

        SinglyNode<Integer> n = sumForward(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 9);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 2);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sumForward(null, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 2);
        Assert.assertEquals(n.getNext().getData().intValue(), 9);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 5);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sumForward(n1, null);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 6);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 7);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sumForward(null, null);
        Assert.assertNull(n);

        // Test different lengths
        n1 = new SinglyNode<>(1);
        n1.appendToTail(7);

        n2 = new SinglyNode<>(2);
        n2.appendToTail(9);
        n2.appendToTail(5);

        n = sumForward(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 3);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 2);
        Assert.assertNull(n.getNext().getNext().getNext());

        n1 = new SinglyNode<>(6);
        n1.appendToTail(1);
        n1.appendToTail(7);

        n2 = new SinglyNode<>(9);
        n2.appendToTail(5);

        n = sumForward(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 7);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 2);
        Assert.assertNull(n.getNext().getNext().getNext());

        // Make result longer
        n1 = new SinglyNode<>(9);
        n1.appendToTail(9);
        n1.appendToTail(9);

        n2 = new SinglyNode<>(9);
        n2.appendToTail(9);

        n = sumReverse(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 8);
        Assert.assertEquals(n.getNext().getData().intValue(), 9);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 0);
        Assert.assertEquals(n.getNext().getNext().getNext().getData().intValue(), 1);
        Assert.assertNull(n.getNext().getNext().getNext().getNext());
    }


    @Test
    public void TestQuestion2_6() throws Exception {
        SinglyNode<Character> n1 = new SinglyNode<>('A');
        n1.appendToTail('B');
        SinglyNode<Character> c = n1.appendToTail('C');
        SinglyNode<Character> end = n1.appendToTail('D');
        end.setNext(c);
        SinglyNode<Character> b = brokenAt(n1);
        Assert.assertNotNull(b);
        Assert.assertEquals(b.getData().charValue(), 'D');

        Assert.assertNull(brokenAt(null));
        Assert.assertNull(brokenAt(new SinglyNode<>('A')));
    }

    private SinglyNode<Integer> sumReverse(SinglyNode<Integer> n1, SinglyNode<Integer> n2) {
        SinglyNode<Integer> n = null;
        if (n1 == null) {
            n = n2;
        } else if (n2 == null) {
            n = n1;
        } else {
            int currentFraction = 0;
            while (n1 != null || n2 != null) {
                int currentSum = 0;
                if (n1 != null) {
                    currentSum += n1.getData();
                    n1 = n1.getNext();
                }
                if (n2 != null) {
                    currentSum += n2.getData();
                    n2 = n2.getNext();
                }
                if (n == null) {
                    n = new SinglyNode<>((currentSum + currentFraction) % 10);
                } else {
                    n.appendToTail((currentSum + currentFraction) % 10);
                }
                currentFraction = (currentSum + currentFraction) / 10;
            }
            if (currentFraction > 0) {
                n.appendToTail(currentFraction);
            }
        }
        return n;
    }

    private SinglyNode<Integer> sumForward(SinglyNode<Integer> n1, SinglyNode<Integer> n2) {
        SinglyNode<Integer> n = null;
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        if (n1 == null) {
            n = n2;
        } else if (n2 == null) {
            n = n1;
        } else {
            while (n1 != null || n2 != null) {
                if (n1 != null) {
                    st1.push(n1.getData());
                    n1 = n1.getNext();
                }
                if (n2 != null) {
                    st2.push(n2.getData());
                    n2 = n2.getNext();
                }
            }
        }
        int currentFraction = 0;
        while (!st1.empty() || !st2.empty()) {
            int currentSum = 0;
            if (!st1.empty()) {
                currentSum += st1.pop();
            }
            if (!st2.empty()) {
                currentSum += st2.pop();
            }
            SinglyNode<Integer> data = new SinglyNode<>((currentSum + currentFraction) % 10);
            if (n == null) {
                n = data;
            } else {
                data.setNext(n);
                n = data;
            }
            currentFraction = (currentSum + currentFraction) / 10;
        }
        if (currentFraction > 0) {
            SinglyNode<Integer> data = new SinglyNode<>(currentFraction);
            data.setNext(n);
            n = data;
        }
        return n;
    }

    private <T extends Comparable<T>> SinglyNode<T> brokenAt(SinglyNode<T> n) {
        if (n == null) {
            return null;
        }
        SinglyNode<T> current = n;
        SinglyNode<T> runner = n.getNext();
        while (current != null) {
            current.setVisited(true);
            if (runner != null && runner.isVisited()) {
                return current;
            }
            current = current.getNext();
            if (runner != null) {
                runner = runner.getNext();
            }
        }
        return null;
    }
}

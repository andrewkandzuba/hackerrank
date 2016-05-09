package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

/**
 * Questions: 2.5(a)
 */
public class TestChapter2_2 {

    @Test
    public void TestQuestion2_5() throws Exception {
        SinglyNode<Integer> n1 = new SinglyNode<>(7);
        n1.appendToTail(1);
        n1.appendToTail(6);

        SinglyNode<Integer> n2 = new SinglyNode<>(5);
        n2.appendToTail(9);
        n2.appendToTail(2);

        SinglyNode<Integer> n = sum(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 2);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 9);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sum(null, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 5);
        Assert.assertEquals(n.getNext().getData().intValue(), 9);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 2);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sum(n1, null);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 7);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 6);
        Assert.assertNull(n.getNext().getNext().getNext());

        n = sum(null, null);
        Assert.assertNull(n);

        // Test different lengths
        n1 = new SinglyNode<>(7);
        n1.appendToTail(1);

        n2 = new SinglyNode<>(5);
        n2.appendToTail(9);
        n2.appendToTail(2);

        n = sum(n1, n2);
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

        n = sum(n1, n2);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getData().intValue(), 2);
        Assert.assertEquals(n.getNext().getData().intValue(), 1);
        Assert.assertEquals(n.getNext().getNext().getData().intValue(), 7);
        Assert.assertNull(n.getNext().getNext().getNext());
    }

    private SinglyNode<Integer> sum(SinglyNode<Integer> n1, SinglyNode<Integer> n2) {
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
                if(n == null){
                    n = new SinglyNode<>((currentSum + currentFraction) % 10);
                } else {
                    n.appendToTail((currentSum + currentFraction) % 10);
                }
                currentFraction = (currentSum + currentFraction) / 10;
            }
        }
        return n;
    }
}

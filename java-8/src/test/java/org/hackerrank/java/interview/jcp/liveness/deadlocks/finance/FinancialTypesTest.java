package org.hackerrank.java.interview.jcp.liveness.deadlocks.finance;

import org.junit.Assert;
import org.junit.Test;

public class FinancialTypesTest {

    @Test
    public void testDollarAmount() throws Exception {
        Amount amount1 = new Amount(1000);
        Amount amount2 = new Amount(2000);
        Amount amount3 = new Amount(500);
        Amount amount4 = new Amount(1000);

        Assert.assertTrue(amount1.compareTo(amount4) == 0);
        Assert.assertTrue(amount1.compareTo(amount2) < 0);
        Assert.assertTrue(amount1.compareTo(amount3) > 0);
    }

}

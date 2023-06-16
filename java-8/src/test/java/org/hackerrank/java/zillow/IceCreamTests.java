package org.hackerrank.java.zillow;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class IceCreamTests {

    private final Solution solution = new Solution();

    @Test
    public void maxItemsTest() {
        Assert.assertEquals(4, solution.maxIceCream(new int[]{1,3,2,4,1}, 7));
    }

    class Solution {
        public int maxIceCream(int[] costs, int coins) {
            Arrays.sort(costs);
            for(int i = 0; i < costs.length; i++){
                int price = costs[i];
                if(coins < price) return i;
                coins -= price;
            }
            return costs.length;
        }
    }
}

package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestHops {

    @Test
    public void test1() {
        //Assert.assertEquals(4, new Hops().solution(new int[]{5, 3, 6, 7, 9}));
        Assert.assertEquals(4, new Hops().solution(new int[]{2, 3}));
    }

    public class Hops {
        int solution(int[] inputArray) {
            int len = inputArray.length;

            if (len == 0) return 1;
            int max_trap = inputArray[0];
            Set<Integer> traps = new HashSet<>();
            for (int j : inputArray) {
                traps.add(j);
                if (j > max_trap) {
                    max_trap = j;
                }
            }

            for(int l = 2; l < max_trap + 1; l++) {
                boolean trapped = false;
                for(int i = 0; i <= max_trap; i+=l) {
                    if (traps.contains(i)){
                        trapped = true;
                        break;
                    }
                }
                if (!trapped){
                    return l;
                }
            }

            return max_trap + 1;
        }
    }
}

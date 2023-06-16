package org.hackerrank.java.interview;

import org.junit.Test;

public class SolutionHitCounter {

    @Test
    public void testHitCounter() {
        SolutionHitCounter hitCounter = new SolutionHitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        hitCounter.getHits(300); // get hits at timestamp 300, return 4.
        hitCounter.getHits(301); // get hits at timestamp 301, return 3.
    }

    public SolutionHitCounter() {

    }

    private final int RANGE_IN_SECONDS = 5 * 60;

    private final int[] timeWindow = new int[RANGE_IN_SECONDS];
    int currentMaxTimestamp = RANGE_IN_SECONDS;
    int currentMinTimestamp = 1;
    
    public void hit(int timestamp) {
        if(timestamp >= currentMaxTimestamp) {
            for(int i = 0; i < RANGE_IN_SECONDS; i++){
                timeWindow[i] = 0;
            }
            int tw = (timestamp / RANGE_IN_SECONDS);
            currentMinTimestamp = tw * RANGE_IN_SECONDS;
            currentMaxTimestamp = (tw + 1) * RANGE_IN_SECONDS;
        }
        int offset = timestamp - currentMinTimestamp;
        timeWindow[offset]++;
    }

    public int getHits(int timestamp) {
        int offset = timestamp - currentMinTimestamp;
        return timeWindow[offset];
    }
}

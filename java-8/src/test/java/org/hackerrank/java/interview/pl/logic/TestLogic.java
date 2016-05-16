package org.hackerrank.java.interview.pl.logic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The tests suite for logical section of the interview
 */
public class TestLogic {

    @Test
    public void testClockHandsAngle() throws Exception {
        assertEquals(7.5d, ClockHands.angleInDegrees(3, 15), 0.0d);
        assertEquals(0.131, ClockHands.angleInRadians(3, 15), 0.0d);

        assertEquals(7.5d, ClockHands.angleInDegrees(15, 15), 0.0d);
        assertEquals(0.131, ClockHands.angleInRadians(15, 15), 0.0d);

        assertEquals(0.0d, ClockHands.angleInDegrees(0, 0), 0.0d);
        assertEquals(0.0d, ClockHands.angleInRadians(0, 0), 0.0d);

        assertEquals(167.5d, ClockHands.angleInDegrees(0, 35), 0.0d);
        assertEquals(2.923d, ClockHands.angleInRadians(0, 35), 0.0d);

        assertEquals(165.0d, ClockHands.angleInDegrees(0, 30), 0.0d);
        assertEquals(2.880d, ClockHands.angleInRadians(0, 30), 0.0d);

        assertEquals(35.0d, ClockHands.angleInDegrees(20, 50), 0.0d);
        assertEquals(0.611d, ClockHands.angleInRadians(20, 50), 0.0d);

        assertEquals(114.5d, ClockHands.angleInDegrees(19, 59), 0.0d);
        assertEquals(1.998d, ClockHands.angleInRadians(19, 59), 0.0d);

        assertEquals(120.0d, ClockHands.angleInDegrees(20, 0), 0.0d);
        assertEquals(2.094d, ClockHands.angleInRadians(20, 0), 0.0d);

        assertEquals(90.0d, ClockHands.angleInDegrees(21, 0), 0.0d);
        assertEquals(1.571d, ClockHands.angleInRadians(21, 0), 0.0d);

        assertEquals(172.5d, ClockHands.angleInDegrees(21, 15), 0.0d);
        assertEquals(3.011d, ClockHands.angleInRadians(21, 15), 0.0d);

        assertEquals(180.0d, ClockHands.angleInDegrees(18, 0), 0.0d);
        assertEquals(3.142d, ClockHands.angleInRadians(18, 0), 0.0d);
    }

    @Test
    public void testClockTimeToTravel() throws Exception {
        assertEquals(35.0d, ClockHands.timeToTravel(210), 0.0d);
        assertEquals(0.0d, ClockHands.timeToTravel(0), 0.0d);
        assertEquals(15.0d, ClockHands.timeToTravel(90), 0.0d);
    }
}

package org.hackerrank.java.interview.pl.logic;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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

    @Test
    public void testPourWater() throws Exception {
        LinkedBlockingDeque<Integer> jug1 = new LinkedBlockingDeque<>(5); // 5 liters jug
        LinkedBlockingDeque<Integer> jug2 = new LinkedBlockingDeque<>(3); // 3 liters jug
        LinkedBlockingDeque<Integer> bucket = new LinkedBlockingDeque<>(1024); // Virtually unlimited in this task

        // 1. Pour water to first jug
        try {
            while (true) {
                jug1.addFirst(1);
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 5);
        assertTrue(jug2.size() == 0);
        assertTrue(bucket.size() == 0);

        // 2. Pour from first jug to second one until second jug is full
        try {
            while (true) {
                Integer portion = jug1.peekLast();
                jug2.addFirst(portion);
                jug1.pollLast();
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 2);
        assertTrue(jug2.size() == 3);
        assertTrue(bucket.size() == 0);

        // 3. Pour from the first jug to a bucket.
        while (jug1.size() > 0){
            bucket.add(jug1.pollLast());
        }
        assertTrue(jug1.size() == 0);
        assertTrue(jug2.size() == 3);
        assertTrue(bucket.size() == 2);

        // 4. Pour remains from the second jug to second jug
        try {
            while (true) {
                Integer portion = jug2.peekLast();
                if(portion == null){
                    break;
                }
                jug1.addFirst(portion);
                jug2.pollLast();
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 3);
        assertTrue(jug2.size() == 0);
        assertTrue(bucket.size() == 2);

        // 5. Pour water to first jug
        try {
            while (true) {
                jug1.addFirst(1);
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 5);
        assertTrue(jug2.size() == 0);
        assertTrue(bucket.size() == 2);

        // 6. Pour water from bucket to second jug
        try {
            while (true) {
                Integer portion = bucket.peekLast();
                if(portion == null){
                    break;
                }
                jug2.addFirst(portion);
                bucket.pollLast();
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 5);
        assertTrue(jug2.size() == 2);
        assertTrue(bucket.size() == 0);

        // 7. Pour water from first jug to second jug
        try {
            while (true) {
                Integer portion = jug1.peekLast();
                if(portion == null){
                    break;
                }
                jug2.addFirst(portion);
                jug1.pollLast();
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 4);
        assertTrue(jug2.size() == 3);
        assertTrue(bucket.size() == 0);

        // 8. Finally pour water from first jug to a bucket
        try {
            while (true) {
                Integer portion = jug1.peekLast();
                if(portion == null){
                    break;
                }
                bucket.addFirst(portion);
                jug1.pollLast();
            }
        } catch (IllegalStateException e){
            System.out.println("Jug is full");
        }
        assertTrue(jug1.size() == 0);
        assertTrue(jug2.size() == 3);
        assertTrue(bucket.size() == 4);
    }
}

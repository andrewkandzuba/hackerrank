package org.hackerrank.java.interview.pl.logic;

import java.math.BigDecimal;

/**
 * Utility class calculates an angel between hour and minute arrows on hand watch
 */
public final class ClockHands {
    private static final double SMALL_HAND_DEGREES_MINUTES = 0.5d; // degrees a minute
    private static final double BIG_HAND_DEGREES_MINUTE = 6d; // degrees a minute
    private static final double BIG_HAND_DEGREES_SECOND = 360 / 60; // degrees per minute

    /**
     * The method calculates the inner angle in degrees between big and small hands on 12 hours clock.
     * @param hours - hours from 0 to 23
     * @param minutes - minutes from 0 to 59
     * @return inner angle in degrees between hands
     * @throws Exception - if parameters do not fit into range
     */
    public static double angleInDegrees(int hours, int minutes) throws Exception {
        hours = validateHours(hours);
        minutes = validateMinutes(minutes);
        double hoursHandToNoon = ((hours * 60 + minutes) * SMALL_HAND_DEGREES_MINUTES) % 360;
        double minutesHandToNoon = ((hours * 60 + minutes) * BIG_HAND_DEGREES_MINUTE) % 360;
        double angel = Math.abs(hoursHandToNoon - minutesHandToNoon);
        return (angel > 180) ? (360 - angel) : angel;
    }

    /**
     * The method calculates the inner angle in radians between big and small hands on 12 hours clock.
     * @param hours - hours from 0 to 23
     * @param minutes - minutes from 0 to 59
     * @return inner angle in radians between hands
     * @throws Exception - if parameters do not fit into range
     */
    public static double angleInRadians(int hours, int minutes) throws Exception {
        return new BigDecimal(String.valueOf(angleInDegrees(hours, minutes) * Math.PI / 180))
                .setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    /**
     * The method calculates time to travel in seconds for minute's hands on 12 hours clock to reach the specified degree from noon.
     * @param degrees -degrees from noon
     * @return time in seconds
     */
    public static double timeToTravel(int degrees){
        return degrees / BIG_HAND_DEGREES_SECOND;
    }

    /**
     * Hourly validator
     * @param hours - value represents hour
     * @return - same value, if it fits into a range: [0..23]
     * @throws Exception - if value doesn't fit into a range
     */
    private static int validateHours(int hours) throws Exception {
        if (hours < 0 || hours > 23) {
            throw new Exception("Only value from [0..23] range is allowed for hour's hand!");
        }
        return hours;
    }

    /**
     * Minutes validator
     * @param minutes - value represents minutes
     * @return - same value, if it fits into a range: [0..59]
     * @throws Exception - if value doesn't fit into a range
     */
    private static int validateMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59) {
            throw new Exception("Only value from [0..59] range is allowed for minute's hand!");
        }
        return minutes;
    }

    private ClockHands() {
    }
}

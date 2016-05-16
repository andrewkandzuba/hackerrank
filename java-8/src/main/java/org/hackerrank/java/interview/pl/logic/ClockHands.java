package org.hackerrank.java.interview.pl.logic;

import java.math.BigDecimal;

/**
 * Utility class calculates an angel between hour and minute arrows on hand watch
 */
public final class ClockHands {
    private static final double HOURLY_HAND_SPEED = 0.5d; // degrees per minute
    private static final double MINUTES_HAND_SPEED = 6.0d; // degrees per minute

    private ClockHands() {
    }

    public static double angleInDegrees(int hours, int minutes) throws Exception {
        hours = validateHours(hours);
        minutes = validateMinutes(minutes);
        double hoursHandToNoon = ((hours * 60 + minutes) * HOURLY_HAND_SPEED) % 360;
        double minutesHandToNoon = ((hours * 60 + minutes) * MINUTES_HAND_SPEED) % 360;
        double angel = Math.abs(hoursHandToNoon - minutesHandToNoon);
        return (angel > 180) ? (360 - angel) : angel;
    }

    public static double angleInRadians(int hours, int minutes) throws Exception {
        return new BigDecimal(String.valueOf(angleInDegrees(hours, minutes) * Math.PI / 180))
                .setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private static int validateHours(int hours) throws Exception {
        if (hours < 0 || hours > 23) {
            throw new Exception("Only value from [0..23] range is allowed for hour's hand!");
        }
        return hours;
    }

    private static int validateMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59) {
            throw new Exception("Only value from [0..59] range is allowed for minute's hand!");
        }
        return minutes;
    }
}

package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;

public class AtanTestNegative {
    @Test
    public void testReturnNanWhenNanArgument() {
        Assert.assertTrue(Double.isNaN(PowerSeries.atan(Double.NaN, 0.1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowingExceptionWhenDeltaIsZero() {
        PowerSeries.atan(0.5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowingExceptionWhenDeltaLessThanZero() {
        PowerSeries.atan(0.5, -1);
    }
}

package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AtanTestPositive {
    private final static double DEFAULT_DELTA = 1e-3;
    private final static double EXTENDED_DELTA = 1e-6;

    private final double arg;
    private final double expected;
    private final double delta;

    public AtanTestPositive(double arg, double expected, double delta) {
        this.arg = arg;
        this.expected = expected;
        this.delta = delta;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {2, 1.1071, DEFAULT_DELTA},
                {-3, -1.2490, DEFAULT_DELTA},
                {0.5, 0.4636, DEFAULT_DELTA},

                // check boundaries
                {-1, -Math.PI / 4, EXTENDED_DELTA},
                {1, Math.PI / 4, EXTENDED_DELTA},
                {0, 0, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, -Math.PI / 2, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Math.PI / 2, EXTENDED_DELTA},
        });
    }

    @Test
    public void testPositive() {
        Assert.assertEquals(expected, PowerSeries.atan(arg, delta), delta);
    }
}

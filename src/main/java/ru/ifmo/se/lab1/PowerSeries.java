package ru.ifmo.se.lab1;

import java.util.function.Function;

public class PowerSeries {
    /**
     * Returns the arc tangent of a value. If the argument is NaN, then the result is NaN.
     *
     * @throws IllegalArgumentException if the delta less than or equal to 0
     * @see <a href="https://proofwiki.org/wiki/Power_Series_Expansion_for_Real_Arctangent_Function">
     * Power Series Expansion for Real Arctangent Function </a>
     */
    public static double atan(double val, double delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("delta should be greater than 0");
        }

        if (Double.isNaN(val)) {
            return Double.NaN;
        }

        if (val >= -1 && val <= 1) {
            return sum(i -> Math.pow(-1, i) * Math.pow(val, 2 * i + 1) / (2 * i + 1), delta);
        } else if (val > 1) {
            return Math.PI / 2 - sum(i -> Math.pow(-1, i) / ((2 * i + 1) * Math.pow(val, 2 * i + 1)), delta);
        } else {
            return -Math.PI / 2 - sum(i -> Math.pow(-1, i) / ((2 * i + 1) * Math.pow(val, 2 * i + 1)), delta);
        }
    }

    private static double sum(Function<Integer, Double> func, double delta) {
        var result = 0.0;
        var prevResult = 0.0;

        var i = 0;
        do {
            prevResult = result;
            result += func.apply(i++);
        } while (Math.abs(prevResult - result) > delta);

        return result;
    }
}

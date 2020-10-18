package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.ifmo.se.lab2.func.Calculator;
import ru.ifmo.se.lab2.func.FunctionBuilder;
import ru.ifmo.se.lab2.func.MathFunction;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TargetFunctionTest {
    private final static double DEFAULT_DELTA = 1e-3;
    private final static double EXTENDED_DELTA = 1e-6;

    private static Calculator calc;

    private final double arg;
    private final double expected;
    private final double delta;
    private final MathFunction targetFunction;

    public TargetFunctionTest(double arg, double expected, double delta) {
        this.arg = arg;
        this.expected = expected;
        this.delta = delta;
        this.targetFunction = new TargetFunction(calc);
    }

    @BeforeClass
    public static void setUpCalculator() {
        var builder = new FunctionBuilder();
        calc = new Calculator(builder.cos(), builder.tan(), builder.csc(), builder.log());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                // x <= 0

                // decreasing part
                {-10.459, 2.329, DEFAULT_DELTA},
                {-3 * Math.PI, 0, EXTENDED_DELTA},
                {-8.43, -0.950, DEFAULT_DELTA},

                // increasing part
                {-7.307, -1.841, DEFAULT_DELTA},
                {-2 * Math.PI, 0, EXTENDED_DELTA},
                {-5.233, 3.338, DEFAULT_DELTA},

                // check boundary
                {0, 0, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},

                // x > 0

                {0.055, 5.868, DEFAULT_DELTA},
                {0.076, 2.987, DEFAULT_DELTA},
                {0.129, 4.917, DEFAULT_DELTA},
                {0.184, 5.571, DEFAULT_DELTA},
                {0.47, 3.121, DEFAULT_DELTA},
                {1.149, 1.575, DEFAULT_DELTA},
                {4.86, 8.461, DEFAULT_DELTA},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
        });
    }

    @Test
    public void testCalculation() {
        Assert.assertEquals(expected, targetFunction.calculate(arg, delta), delta);
    }
}

package ru.ifmo.se.lab2.func;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class FunctionBuilderTest {
    @Test
    public void testCosine() {
        var arg = 42;
        var delta = 0.001;

        var sinMock = Mockito.mock(MathFunction.class);
        Mockito.when(sinMock.calculate(Math.PI / 2 - arg, delta))
                .thenReturn(42.0);

        var builder = new FunctionBuilder(sinMock, Mockito.mock(MathFunction.class));
        Assert.assertEquals(builder.cos().calculate(arg, delta), 43.0, delta);
    }

    @Test
    public void testCosecant() {
        var arg = 42;
        var delta = 0.001;

        var sinMock = Mockito.mock(MathFunction.class);
        Mockito.when(sinMock.calculate(arg, delta))
                .thenReturn(10.0);

        var builder = new FunctionBuilder(sinMock, Mockito.mock(MathFunction.class));
        Assert.assertEquals(builder.csc().calculate(arg, delta), 0.1, delta);
    }

    @Test
    public void testTan() {
        var arg = 42;
        var delta = 0.001;

        var sinMock = Mockito.mock(MathFunction.class);
        Mockito.when(sinMock.calculate(arg, delta))
                .thenReturn(84.0);
        Mockito.when(sinMock.calculate(Math.PI / 2 - arg, delta))
                .thenReturn(42.0);

        var builder = new FunctionBuilder(sinMock, Mockito.mock(MathFunction.class));
        Assert.assertEquals(builder.tan().calculate(arg, delta), 2.0, delta);
    }
}

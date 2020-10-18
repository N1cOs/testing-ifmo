package ru.ifmo.se.lab2.func;

public class Calculator {
    private final MathFunction cosFunc;
    private final MathFunction tanFunc;
    private final MathFunction cscFunc;
    private final MathFunction logFunc;

    public Calculator(MathFunction cosFunc, MathFunction tanFunc, MathFunction cscFunc,
                      MathFunction logFunc) {
        this.cosFunc = cosFunc;
        this.tanFunc = tanFunc;
        this.cscFunc = cscFunc;
        this.logFunc = logFunc;
    }

    public double cos(double arg, double delta) {
        return cosFunc.calculate(arg, delta);
    }

    public double tan(double arg, double delta) {
        return tanFunc.calculate(arg, delta);
    }

    public double csc(double arg, double delta) {
        return cscFunc.calculate(arg, delta);
    }

    public double log(double arg, double delta) {
        return logFunc.calculate(arg, delta);
    }

    public double log(double base, double arg, double delta) {
        return logFunc.calculate(arg, delta) / logFunc.calculate(base, delta);
    }
}

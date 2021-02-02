package com.bntang666.java8.objectreference;

/**
 * @author BNTang
 */
public class CalculateMain {
    public static void main(String[] args) {

        CalculateString calculateString = new CalculateString();
        useCalculate(calculateString::printCalculate);
    }

    private static void useCalculate(CalculateInterface calculateInterface) {
        calculateInterface.calculate(1, 2);
    }
}
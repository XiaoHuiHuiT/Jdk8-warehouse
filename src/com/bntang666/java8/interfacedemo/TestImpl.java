package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public class TestImpl implements TestInterfaceOne, TestInterfaceTwo {
    @Override
    public void show() {
        System.out.println("Show Method Run!");
    }
}
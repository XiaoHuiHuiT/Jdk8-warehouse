package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public class TestMain {

    public static void main(String[] args) {
        TestInterfaceImpl testInterface = new TestInterfaceImpl();

        testInterface.showOne();
        TestInterface.staticMethod();
    }

}
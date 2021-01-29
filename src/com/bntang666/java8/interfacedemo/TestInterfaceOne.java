package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public interface TestInterfaceOne {

    void show();

    default void method() {
        System.out.println("TestInterfaceOne In The Default Method run");
    }

    static void test() {
        System.out.println("TestInterfaceOne In The Static Method run");
    }

}
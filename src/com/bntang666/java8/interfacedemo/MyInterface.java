package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public interface MyInterface {

    void showOne();

    void showTwo();

    default void showThree() {
        System.out.println("Show Three");
    }
}
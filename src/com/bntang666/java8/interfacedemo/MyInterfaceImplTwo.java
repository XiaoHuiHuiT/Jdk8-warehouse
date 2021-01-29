package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public class MyInterfaceImplTwo implements MyInterface {
    @Override
    public void showOne() {
        System.out.println("MyInterfaceImplTwo One Show");
    }

    @Override
    public void showTwo() {
        System.out.println("MyInterfaceImplTwo Two Show");
    }
}
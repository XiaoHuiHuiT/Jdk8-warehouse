package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public class MyInterfaceImplOne implements MyInterface {
    @Override
    public void showOne() {
        System.out.println("MyInterfaceImplOne One Show");
    }

    @Override
    public void showTwo() {
        System.out.println("MyInterfaceImplOne Two Show");
    }

    @Override
    public void showThree() {
        System.out.println("MyInterfaceImplOne Three Show");
    }
}
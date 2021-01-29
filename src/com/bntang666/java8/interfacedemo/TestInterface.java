package com.bntang666.java8.interfacedemo;

/**
 * @author BNTang
 */
public interface TestInterface {

    default void showOne() {
        System.out.println("showOne 开始执行");
        show();
        method();
        System.out.println("showOne 结束执行");
    }

    static void staticMethod() {
        System.out.println("staticMethod 开始执行");
        // show();
        method();
        System.out.println("staticMethod 结束执行");
    }

    private void show() {
        System.out.println("abc");
        System.out.println("abc");
        System.out.println("abc");
    }

    private static void method() {
        System.out.println("abc");
        System.out.println("abc");
        System.out.println("abc");
    }
}
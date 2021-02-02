package com.bntang666.java8.methodreferences;

/**
 * @author BNTang
 */
public class PrintableMain {
    public static void main(String[] args) {
        // 方法引用符：:: 参数传给了 System 当中
        usePrintable(System.out::println);
    }

    private static void usePrintable(Printable printable) {
        printable.printString("test");
    }
}
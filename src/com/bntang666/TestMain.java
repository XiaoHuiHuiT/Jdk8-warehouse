package com.bntang666;

import com.bntang666.java8.interfacedemo.TestImpl;
import com.bntang666.java8.interfacedemo.TestInterfaceOne;
import com.bntang666.java8.interfacedemo.TestInterfaceTwo;

/**
 * @author BNTang
 */
public class TestMain {
    public static void main(String[] args) {
        TestInterfaceOne interfaceOne = new TestImpl();
        interfaceOne.show();
        interfaceOne.method();

        TestInterfaceOne.test();
        // interfaceOne.test();

        TestInterfaceTwo.test();
    }
}
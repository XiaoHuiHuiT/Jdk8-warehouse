package com.bntang666.java8.instancereference;

/**
 * @author BNTang
 */
public class MyStringMain {
    public static void main(String[] args) {
        useMyString(String::substring);
    }

    private static void useMyString(MyString myString) {
        System.out.println(myString.mySubString("HelloWorld", 2, 5));
    }
}
package com.bntang666.java8.methodreferences;

/**
 * @author BNTang
 */
public class ConverterMain {
    public static void main(String[] args) {
        useConverter(Integer::parseInt);
    }

    private static void useConverter(Converter converter) {
        System.out.println(converter.convert("6666"));
    }
}
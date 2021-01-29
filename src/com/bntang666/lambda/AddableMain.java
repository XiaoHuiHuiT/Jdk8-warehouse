package com.bntang666.lambda;

/**
 * @author BNTang
 */
public class AddableMain {
    public static void main(String[] args) {
        useAddable((x, y) -> x + y);
    }

    private static void useAddable(Addable addable) {
        int sum = addable.add(10, 20);
        System.out.println(sum);
    }
}
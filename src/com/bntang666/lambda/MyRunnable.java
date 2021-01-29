package com.bntang666.lambda;

/**
 * @author BNTang
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("thread -> " + Thread.currentThread().getName() + "启动了");
    }
}
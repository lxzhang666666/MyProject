package com.myself.project;

import java.util.concurrent.CountDownLatch;

public class CountDownLachDemo {


    public static void main(String[] args) {

        CountDownLatch downLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "离开");
                downLatch.countDown();
            }, String.valueOf(i)).start();

        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "+++++++++++++++班长关门走人");


    }


}

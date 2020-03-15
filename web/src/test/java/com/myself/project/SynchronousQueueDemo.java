package com.myself.project;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "  put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "  put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "  put 3");
                queue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, String.valueOf(666)).start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "  task ");
                System.out.println(queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "  task ");
                System.out.println(queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "  task ");
                System.out.println(queue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, String.valueOf(999)).start();
    }

}

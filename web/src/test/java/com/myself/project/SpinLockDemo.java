package com.myself.project;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("thread = " + Thread.currentThread().getName() + "  come in");
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println("TimeUnit = " + System.currentTimeMillis());
        }

    }

    public void myUnLock(){

        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + " invoke myUnLock");
    }

    public static void main(String[] args) {
        SpinLockDemo lockDemo = new SpinLockDemo();

        new Thread(()->{
            lockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockDemo.myUnLock();

        },"AAA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            lockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockDemo.myUnLock();
        },"BBB").start();



    }
}

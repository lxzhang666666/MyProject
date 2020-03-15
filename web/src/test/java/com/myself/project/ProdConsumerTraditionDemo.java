package com.myself.project;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 一个初始值为0的变量  两个线程简体操作  一个加一  一个减一  来5轮
 */
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {

        ShareData data = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5 ; i++){
                try {
                    data.incrNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Cuntomer").start();

        new Thread(()->{
            for (int i = 0; i < 5 ; i++){
                try {
                    data.decreNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"Tradition").start();

    }

}

class ShareData{

    private int num = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void incrNum() throws InterruptedException {
        lock.lock();
        try {
            //check(判断)
            while( num != 0 ){
                //等待，生产
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+" num = " + num);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public void decreNum() throws InterruptedException {
        lock.lock();
        try {
            //check(判断)
            while( num == 0 ){
                //等待，生产
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+" num = " + num);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }



}

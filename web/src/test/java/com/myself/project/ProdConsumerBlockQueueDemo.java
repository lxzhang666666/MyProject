package com.myself.project;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile / CAS / atomicInteger / BlockQueue / 线程交互 原子引用
 */
public class ProdConsumerBlockQueueDemo {

    public static void main(String[] args) {
        MyRes res = new MyRes(new ArrayBlockingQueue<>(18));

        new Thread(()->{
            try {
                res.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            try {
                res.consum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        res.stop();

    }

}

class MyRes{

    private volatile boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> queue = null;

    public  MyRes (BlockingQueue<String> queue){
        this.queue = queue;
        System.out.println(queue.getClass().getName());
    }

    public void prod () throws InterruptedException {

        String data = null;
        boolean value ;
        while (FLAG){
            data =  atomicInteger.incrementAndGet() + "";
            value = queue.offer(data,2L, TimeUnit.SECONDS);
            if(value){
                System.out.println(Thread.currentThread().getName() + " 插入队列 " +data+ "  成功");
            }else {
                System.out.println(Thread.currentThread().getName() + " 插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "生产完成   停止");

    }

    public void consum () throws InterruptedException {

        String value = null;
        while (FLAG){

            value = queue.poll(2L, TimeUnit.SECONDS);

            if( value == null || "".equalsIgnoreCase(value)){
                FLAG = false ;
                System.out.println(Thread.currentThread().getName() + " 消费队列 超时 退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费队列 " + value+ " 成功");

        }

    }

    public void stop (){
        FLAG = false;
    }
}
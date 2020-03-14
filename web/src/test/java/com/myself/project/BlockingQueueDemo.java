package com.myself.project;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // List list = new ArrayList;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("**********会抛异常************");

        //增加元素
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));
        System.out.println(blockingQueue.add("3"));
        //取出队列头元素
        System.out.println(blockingQueue.element());
        //取出队列头元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println("*********特殊值*************");

        //插入元素
        System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("3"));
        System.out.println(blockingQueue.offer("7"));
        //检查
        System.out.println(blockingQueue.peek());
        //移除
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println("**********阻塞************");

        //阻塞插值（插不进去阻塞）
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("**********************");
        //阻塞移除（移除不掉阻塞）
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        System.out.println("************超时**********");
        //插入元素（超时则不加）
        System.out.println(blockingQueue.offer("1",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("2",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("3",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("7",2L, TimeUnit.SECONDS));
        //移除超时则不移除
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }

}

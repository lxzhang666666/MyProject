package com.myself.project;

import java.util.concurrent.*;

/**
 * public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler)
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        /*ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5); (设置最大线程数)
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();(单线程重复用)
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();(带缓存自动扩容重复用)*/

        System.out.println(Runtime.getRuntime().availableProcessors());//系统核心数  CPU 密集型  核心数+1
        // IO 密集型 尽可能配多  核心数*2      核心数 除以 1 - 阻塞系数 （0.8 - 0.9）

        ExecutorService threadPool = new ThreadPoolExecutor(
                3,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());//(最多支持 max + 阻塞队列数 线程数 就执行拒绝策略 )
                // ThreadPoolExecutor.AbortPolicy() 抛异常
                // ThreadPoolExecutor.CallerRunsPolicy()不会抛异常 回退给调用者 main线程
                // ThreadPoolExecutor.DiscardOldestPolicy() 抛弃等待最久的
                // ThreadPoolExecutor.DiscardPolicy() 抛弃
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+"  办理业务"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }


}

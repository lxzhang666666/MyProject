package com.myself.project;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 两个或以上线程 在执行过程中 对资源争抢造成一种互相等待的现象  若在无外力干涉下 无法继续下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldThread(lockA,lockB),"ThreadA").start();
        new Thread(new HoldThread(lockB,lockA),"ThreadB").start();

        // jps -l 查看 java版 ps  例如 Linux ps -ef
        //jstack  加进程号
    }

}

@AllArgsConstructor
class HoldThread implements Runnable{

    private String lockA;

    private String lockB;

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + " 自己持有："+lockA+"尝试持有 " + lockB);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + " 自己持有："+lockB +"尝试持有 " + lockA);
            }
        }

    }
}

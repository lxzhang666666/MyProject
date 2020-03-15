package com.myself.project;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchroized 和 locj 有什么区别  lock有什么好处  举例说明
 * <p>
 * 1. synchronized 是java关键字  jvm层面
 * monitorenter(底层通过monitor对象来完成 ，
 * 其实 wait/notify 等方法也是通过依赖monitor对象 只有在同步代码块或者同步方法中才能调用 wait/notify 等方法)
 * monitorexit
 * lock是工具类 java.until.concurrent.locks.lock  是 api 层面的锁
 * 2.使用方法
 * synchronized  不需要去释放锁 synchronized代码块执行完 系统会自动让线程释放对锁的占用
 * <p>
 * lock则需要手动释放锁 若锁没有自动释放 就有可能出现死锁现象
 * <p>
 * 3.等待是否可以中断
 * synchronized 不可中断
 * ReentrantLock 可以中断  1.设置超时方法  tryLock(long timeout ,TimeUtil util)
 * `                       2.lockInterruptibly()代码块中  调用Interrupt()中断
 * 4.加锁是否公平
 * synchronized 非公平锁
 * ReentrantLock 默认非公平  放入true 公平锁
 * <p>
 * 5.锁要绑定多个条件condition
 * synchronized 没有
 * reentrantLock 用来实现分组唤醒需要唤醒的线程  可以实现精确唤醒
 */
public class SyncAndLockDemo {

    public static void main(String[] args) {
        ShareResource resource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                resource.prints();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                resource.prints();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                resource.prints();
            }
        }, "C").start();


    }


}

class ShareResource {

    private int num = 1;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void prints() {
        switch (Thread.currentThread().getName()) {
            case "A":
                lock.lock();
                try {
                    while (num != 1) {
                        condition1.await();
                    }
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + " num =" + num);
                    }
                    num = 2;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                break;
            case "B":
                lock.lock();
                try {
                    while (num != 2) {
                        condition2.await();
                    }
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " num =" + num);
                    }
                    num = 3;
                    condition3.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                break;
            case "C":
                lock.lock();
                try {
                    while (num != 3) {
                        condition3.await();
                    }
                    for (int i = 0; i < 15; i++) {
                        System.out.println(Thread.currentThread().getName() + " num =" + num);
                    }
                    num = 1;
                    condition1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                break;
        }


    }

    public void prints5() {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " num =" + num);
            }
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void prints10() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();

            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " num =" + num);
            }
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void prints15() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + " num =" + num);
            }
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void incrNum() throws InterruptedException {
        lock.lock();
        try {
            //check(判断)
            while (num != 0) {
                //等待，生产
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void decreNum() throws InterruptedException {
        lock.lock();
        try {
            //check(判断)
            while (num == 0) {
                //等待，生产
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
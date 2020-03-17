package com.myself.project;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * 幽灵引用  虚引用
 *
 * java 提供4种引用类型 在垃圾回收的时候都有各自的特点
 * ReferenceQueue 是用来配合引用工作 没有 ReferenceQueue一样可以工作
 *创建引用时可以指定关联到引用队列 当GC释放对象内存时  会将引用加入到引用队列
 * 如果发现某个虚引用加入到引用队列   那么就可以在所引用的对象释放内存前采取必要的行动
 * 这相当于一种通知机制
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj,referenceQueue);
        System.out.println("obj = " + obj);
        System.out.println("phantomReference = " + phantomReference.get());
        System.out.println("referenceQueue = " + referenceQueue.poll());


        obj = null;

        System.gc();
        System.out.println("___________________GC____________________ ");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("obj = " + obj);
        System.out.println("phantomReference = " + phantomReference.get());
        System.out.println("referenceQueue = " + referenceQueue.poll());


    }

}

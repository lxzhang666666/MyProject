package com.myself.project;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceQueueDemo {

    public static void main(String[] args) {

        Object obj = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(obj, referenceQueue);

        System.out.println("obj = " + obj);
        System.out.println("weakReference = " + weakReference.get());
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
        System.out.println("weakReference = " + weakReference.get());
        System.out.println("referenceQueue = " + referenceQueue.poll());
    }

}

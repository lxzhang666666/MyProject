package com.myself.project;

/**
 * 强引用  GC不回收
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        Object obj1 = obj;  //强引用  不会垃圾回收
        obj = null ;
        System.gc();
        System.out.println(obj);
        System.out.println(obj1);
    }

}

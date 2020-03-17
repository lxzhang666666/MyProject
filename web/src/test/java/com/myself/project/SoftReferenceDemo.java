package com.myself.project;

import java.lang.ref.SoftReference;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 软引用 内存够用则不回收 就是硬引用  内存不足则回收
 * 启动参数  -Xms5m -Xmx5m -XX:+PrintGCDetails
 */
public class SoftReferenceDemo {

    public static void softRef(){
        Object obj = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj);
        System.out.println("obj = " + obj);
        System.out.println("reference = " + reference.get());
        obj = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("obj = " + obj);
            System.out.println("reference = " + reference.get());
        }



    }

    public static void main(String[] args) {

        //存放图片时  文件太大 用软引用 内存不足则自动回收
        Map<String, SoftReference<BitSet>> hashMap = new HashMap<>();

        /*Object obj = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj);
        System.out.println("obj = " + obj);
        System.out.println("reference = " + reference.get());
        obj = null;
        System.gc();

        System.out.println("obj = " + obj);
        System.out.println("reference = " + reference.get());*/

        softRef();

    }

}

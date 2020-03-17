package com.myself.project;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 弱引用
 */
public class WeakReferenceDome {

    public static void main(String[] args) {

        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj);
        System.out.println("obj = " + obj);
        System.out.println("reference = " + weakReference.get());
        obj = null;
        System.gc();
        System.out.println("****************************************");

        System.out.println("obj = " + obj);
        System.out.println("reference = " + weakReference.get());

       myHashMap();

       myWeakHashMap();
    }

    private static void myWeakHashMap(){
        System.out.println("****************************************");
        //弱引用map  key无效则回收
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer integer = new Integer(1);
        String value = "1" ;

        weakHashMap.put(integer,value);

        System.out.println("weakHashMap = " + weakHashMap);

        integer = null ;

        System.out.println("weakHashMap = " + weakHashMap);

        System.gc();
        System.out.println("****************************************");

        System.out.println("weakHashMap = " + weakHashMap + weakHashMap.size());

    }


    private static void myHashMap(){
        System.out.println("****************************************");
        Map<Integer, String> hashMap = new HashMap<>();
        Integer integer = new Integer(1);
        String value = "1" ;

        hashMap.put(integer,value);

        System.out.println("hashMap = " + hashMap);

        integer = null ;

        System.out.println("hashMap = " + hashMap);

        System.gc();
        System.out.println("****************************************");
        System.out.println("hashMap = " + hashMap + hashMap.size());

    }

}

package com.myself.project;


/**
 * 1.虚拟机栈（栈帧中的局部变量区，也叫局部变量表）中的引用对象
 * 2.方法区中的静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈JNI （native方法）引用的对象
 */
public class GCRootDemo {

    private byte[] bytes = new byte[10*1024];

    //方法区中的静态属性引用的对象
    private static Object object;

    //方法区中常量引用的对象
    private static final Object object2 = new Object();

    public static void gc1() {
        //虚拟机栈（栈帧中的局部变量区，也叫局部变量表）中的引用对象
        GCRootDemo gcRootDemo = new GCRootDemo();
        System.gc();
        System.out.println(" 第一次 GC 完成  " );


    }

    public static void main(String[] args) {
        gc1();
    }

}

package com.myself.project;


import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * 启动参数 ：
 *   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 故障现象：
 *      java.lang.OutOfMemoryError: Direct buffer memory
 *
 * 导致原因：
 *
 *  写NIO程序经常使用ByteBuffer来读取或写入数据  这是一种基于通道（channel）和缓冲区（buffer）的I/O方式
 *  它可以使用Native函数直接调用堆外内存 然后通过调用一个存在java堆内的DirectByteBuffer对象作为这块内存的引用进行操作
 *  这样能在一些场景中提高使用性能 因为避免了java堆和Native堆中 来回复制数据
 *
 *  ByteBuffer.allocate(capacity)  第一种方式是分配JVM堆内存，属于GC管理范围，由于需要拷贝所以速度相对较慢
 *
 *   ByteBuffer.allocateDirect(capacity) 一种当时是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度较快
 *
 *   但如果不断分配本地内存，堆内存很少使用 那么JVM就不需要进行GC  DirectByteBuffer对象们就不会被回收
 *   这时堆内存充足 但本地内存可能已经使用完了  再次尝试分配内存就会出现OutOfMemoryError 那么程序就直接崩溃了
 *
 *
 */
public class DircetBufferMemoryDemo {

    public static void main(String[] args) {

        System.out.println( "配置的 MaxDirectMemory : " + (VM.maxDirectMemory() /(double)1024 /1024)  + "MB");

       /* ByteBuffer.allocate()
        ByteBuffer.allocateDirect()*/
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);

    }


}

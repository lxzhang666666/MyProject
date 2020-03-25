package com.myself.project;


/**
 * 启动参数 ：
 * 故障现象：
 *      java.lang.OutOfMemoryError: Unable Create New Native Thread
 *高并发请求服务器时 经常会出现 java.lang.OutOfMemoryError: Unable Create New Native Thread
 * 准确的讲该 Native thread 异常对应的系统有关
 *
 * 导致原因：
 *
 *   1.你的应用创建了太多线程，一个应用创建了多个线程，超过了系统承载极限
 *   2.你的服务器不允许你的应用程序创建这么多线程 Linux系统默认允许单个进程创建1024个线程
 *   你的应用超过这个个数就会报
 *
 *解决办法 ：
 *  1.想办法降低你的应用创建线程数，分析应用是否真的需要这么多线程 如果不是则改代码降低线程数
 *  2.对于有的应用确实需要这么多线程  远超过Linux系统默认的1024个线程  可以通过修改linux服务器配置  非ROOT用户明显
 *    Linux  ulimt -u
 *      vim /etc/security/limits.d/20-nproc.conf   修改新增一个用户的线程数
 *
 */
public class UnableCreateNewNativeThreadDemo {

    public static void main(String[] args) {

        for (int i = 1; ; i++) {
            System.out.println("*******************************i = " + i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },i+"").start();
        }
    }
}

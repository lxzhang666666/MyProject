package com.myself.project;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * * 启动参数 ：
 * *  -XX:MaxMetaspaceSize=7m -XX:MetaspaceSize=7m
 * * 故障现象：
 * *      java.lang.OutOfMemoryError: Metaspace
 * *
 * * 导致原因：
 */
public class MetaspaceOOMDemo {

    static class OOMTest {}

    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("******************i = " + i);
            e.printStackTrace();
        }
    }

}

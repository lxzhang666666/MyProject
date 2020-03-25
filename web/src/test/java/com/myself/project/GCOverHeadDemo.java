package com.myself.project;

import java.util.ArrayList;
import java.util.List;


/**
 * jvm 启动参数
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 垃圾回收事倍功半  垃圾回收占很多时间 但是并未回收很多空间
 */
public class GCOverHeadDemo {

    public static void main(String[] args) {

        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("****************************i = " + i);
            e.printStackTrace();
            throw e;
        }

    }

}

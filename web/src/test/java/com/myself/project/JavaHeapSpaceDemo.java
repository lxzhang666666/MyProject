package com.myself.project;

import java.util.Random;

/**
 * 启动参数
 *
 * -Xms5m -Xmx5m -XX:+PrintGCDetails
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {

        byte[] bytes = new byte[80 * 1024 * 1024];

        String str = "asdasd";

        while (true){
            str += str + new Random().nextInt(111111) + new Random().nextInt(111111) ;
            str.intern();
        }

    }

}

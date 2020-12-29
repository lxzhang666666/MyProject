package com.myself.project;

/**
 * Created by Administrator on 2020/5/5.
 */
public class SingletonDemo {

    private static SingletonDemo singleton = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "  singleton" );
    }

    public static SingletonDemo getSingleton(){
        if(singleton == null){
            synchronized (SingletonDemo.class){
                if(singleton == null){
                    singleton =  new SingletonDemo();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo singleton = SingletonDemo.getSingleton();
                System.out.println("singleton = " + singleton);
            },String.valueOf(i)).start();
        }

    }

}

package com.myself.project.impl;

import com.myself.project.AbstractBiz;
import org.springframework.stereotype.Service;

@Service
public class TestBiz1 extends AbstractBiz {
    private static TestBiz1 instance;

    private TestBiz1() {
    }


    public static TestBiz1 getInstance() {
        if (instance == null) {
            instance = new TestBiz1();
        }
        return instance;
    }


    @Override
    public void doAction(String str){
        System.out.println("str = " + str);
    }
}

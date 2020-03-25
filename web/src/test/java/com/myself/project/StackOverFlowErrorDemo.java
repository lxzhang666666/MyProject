package com.myself.project;

/**
 *
 */
public class StackOverFlowErrorDemo {

    public static void main(String[] args) {

        StackOverFlowErrorFun();

    }

    private static void StackOverFlowErrorFun() {
        StackOverFlowErrorFun(); // 递归 StackOverFlowError
    }


}

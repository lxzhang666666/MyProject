package com.myself.project;

import com.myself.project.common.entity.Project;

public class TestTransferValue {

    public void test1(int age){
        age = 30 ;
        System.out.println("age1 = " + age);
    }

    public void test2(Project project){
        project.setName("30")  ;
    }

    public void test3(String str){
        str = "xxx" ;
        System.out.println("str1 = " + str);
    }


    public static void main(String[] args) {
        TestTransferValue transferValue = new TestTransferValue();
        int age = 20 ;
        transferValue.test1(age);
        System.out.println("age = " + age);

        Project project = new Project();
        project.setName("abc");
        transferValue.test2(project);
        System.out.println("project = " + project);

        String str = "abc";
        transferValue.test3(str);
        System.out.println("str = " + str);

    }


}

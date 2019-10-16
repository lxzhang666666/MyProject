package com.myself.project;

import com.myself.project.common.entity.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEnumTest {

    @Autowired
    private MyBiz myBiz;

    @Test
    public void test(){
        List<Project> data = myBiz.getData();
        System.out.println("data = " + data);
    }

}

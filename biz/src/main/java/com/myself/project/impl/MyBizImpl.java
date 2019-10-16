package com.myself.project.impl;

import com.myself.project.AbstractBiz;
import com.myself.project.MyBiz;
import com.myself.project.eunm.TestEnum;
import com.myself.project.dao.ProjectDao;
import com.myself.project.common.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class MyBizImpl implements MyBiz {

    @Autowired(required=true)
    private ProjectDao projectDao;

    public List<Project> getData() {
        String  asd = "123";
        try {
            AbstractBiz action = TestEnum.getActionByCode(TestEnum.CHECK.getCode());
            action.doAction(asd);
            Method check = this.getClass().getDeclaredMethod(TestEnum.CHECK.getCode(), String.class);
            check.setAccessible(true);
            Object invoke = check.invoke(this, asd);
            System.out.println("invoke = " + invoke);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return projectDao.selectAllProject();
    }

    public boolean insertData(String name) {
        int i = projectDao.insertProject(name);
        return i > 0;
    }

    public boolean updateData(String name,String id) {
        int i = projectDao.updateProject(name,id);
        return i > 0 ;
    }




    private Boolean check(String asd){
        System.out.println("asd = " + asd);
        return true;
    }
}

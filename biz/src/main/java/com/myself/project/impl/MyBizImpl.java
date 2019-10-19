package com.myself.project.impl;

import com.alibaba.fastjson.JSON;
import com.myself.project.AbstractBiz;
import com.myself.project.MyBiz;
import com.myself.project.common.entity.Project;
import com.myself.project.common.utils.RedisUtil;
import com.myself.project.dao.ProjectDao;
import com.myself.project.eunm.TestEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyBizImpl implements MyBiz {

    protected static Logger logger = LoggerFactory.getLogger(MyBizImpl.class);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    RedisUtil redisUtil;

    public List<Project> getData(String str) {
        List<Project> projects = new ArrayList<>();
        try {
            AbstractBiz action = TestEnum.getActionByCode(TestEnum.CHECK.getCode());
            action.doAction(str);
            Method check = this.getClass().getDeclaredMethod(TestEnum.CHECK.getCode(), String.class);
            check.setAccessible(true);
            projects = (List<Project>) check.invoke(this, str);
            if (projects == null) {
                logger.info(this.getClass().getSimpleName() + "#" + Thread.currentThread().getStackTrace()[1].getMethodName() + "缓存数据为空，查库");
                projects = projectDao.selectAllProject();
                redisUtil.set(str,projects,3600);
            }
        } catch (Exception e) {
            logger.error(
                    this.getClass().getSimpleName() + "#" + Thread.currentThread().getStackTrace()[1].getMethodName() + e);
        }
        return projects;
    }

    public boolean insertData(String name) {
        int i = projectDao.insertProject(name);
        return i > 0;
    }

    public boolean updateData(String name, String id) {
        int i = projectDao.updateProject(name, id);
        return i > 0;
    }

    private Object check(String asd) {
        Object projects = redisUtil.get(asd);
        logger.info(this.getClass().getSimpleName() + "#" + Thread.currentThread().getStackTrace()[1].getMethodName() + "访问" + asd + "数据"+JSON.toJSONString(projects));
        return projects;
    }
}

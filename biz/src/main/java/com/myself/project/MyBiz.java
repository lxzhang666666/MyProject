package com.myself.project;


import com.myself.project.common.entity.Project;

import java.util.List;

public interface MyBiz {
    List<Project> getData(String str);

    boolean insertData(String name);

   boolean updateData(String name,String id);


}

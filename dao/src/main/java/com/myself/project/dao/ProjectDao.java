package com.myself.project.dao;

import com.myself.project.common.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface ProjectDao extends Mapper<Project>{

   List<Project> selectAllProject();

   int insertProject(String name);

   int updateProject(@Param("name") String name ,@Param("id") String id);
}

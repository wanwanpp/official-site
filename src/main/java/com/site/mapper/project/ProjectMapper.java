package com.site.mapper.project;

import com.site.model.project.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wang0 on 2016/9/21.
 */
@Mapper
public interface ProjectMapper {


    @Select("select * from projects")
    List<Project> findAll();

    @Select("select * from projects limit 6")
    List<Project> findTopSix();

}

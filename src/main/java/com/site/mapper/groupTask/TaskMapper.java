package com.site.mapper.groupTask;

import com.site.model.groupTask.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wang0 on 2016/9/21.
 */
@Mapper
public interface TaskMapper {

    @Select("select * from group_task where status=0")
    List<Task> findNotFinished();


    @Select("select * from group_task where status=1")
    List<Task> findFinished();

    @Insert("insert into group_task(group,startdate,enddate,memberid,status) values(#{group},#{startdate},#{enddate},#{memberid},#{status})")
    @Options(useGeneratedKeys = true)
    void insertTask(Task task);

}

package com.site.mapper;

import com.site.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wang0 on 2016/9/20.
 */
@Mapper
public interface MemberMapper {

    @Select("select * from member")
    List<Member> findAll();

    @Select("select * from member where login_name=#{name}")
    Member findByLoginName(@Param("name") String name);

    @Select("select * from member where stu_id=#{stu_id}")
    Member findByStuId(@Param("stu_id") Long stu_id);


}

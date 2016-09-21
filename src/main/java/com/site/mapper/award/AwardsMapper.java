package com.site.mapper.award;

import com.site.model.award.Awards;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wang0 on 2016/9/21.
 */
@Mapper
public interface AwardsMapper {

    @Select("select * from awards")
    List<Awards> findAll();

    @Select("select * from awards limit 6")
    List<Awards> findTopSix();
}

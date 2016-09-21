package com.site.mapper.login;

import com.site.model.login.Roles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wang0 on 2016/9/21.
 */
public interface RolesMapper {
    @Select("select r.* from roles r where r.id in (select a.role_id from role_member a where a.member_id=#{memberId})")
    List<Roles> findByMemberid(@Param("memberId") Long memberId);
}


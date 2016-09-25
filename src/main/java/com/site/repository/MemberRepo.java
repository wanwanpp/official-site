package com.site.repository;


import com.site.model.sign.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wang0 on 2016/9/13.
 */
public interface MemberRepo extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.stuId =?1")
    Member findByStuId(Long id);

    @Query("select m from Member m where m.name =?1")
    Member findByStuName(String name);

    List<Member> findByGrade(int grade);

    @Query("select m.name from Member m where m.grade = ?1")
    List<String> findNameByGrade(int grade);

    @Query("select m.name from Member m")
    List<String> findAllNames();

    @Modifying
    @Transactional
    @Query("update Member m set m.isstart=1 where m.name=?1")
    int setIsStart(String name);

    @Modifying
    @Transactional
    @Query("update Member m set m.isstart=0 where m.name=?1")
    int setIsEnd(String name);


    Member findByName(String name);
}

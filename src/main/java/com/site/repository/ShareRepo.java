package com.site.repository;

import com.site.model.share.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wang0 on 2016/9/25.
 */
public interface ShareRepo extends JpaRepository<Share,Long> {

    @Query("select s from Share s where s.groupid=?1 and s.fanwei=1 group by createTime")
    List<Share> findIsInGroup(Integer group);

    @Query("select s from Share s where s.fanwei=2 group by createTime")
    List<Share> findIsInTeam();

}

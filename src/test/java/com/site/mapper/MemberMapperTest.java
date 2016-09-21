package com.site.mapper;

import com.site.Springboot;
import com.site.mapper.login.MemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wang0 on 2016/9/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Springboot.class)
public class MemberMapperTest {


    @Test
    public void findByStuId() throws Exception {
        System.out.println(memberMapper.findByStuId(201531060616L));
    }


    @Autowired
    private MemberMapper memberMapper;


    @Test
    public void findAll() throws Exception {

        System.out.println(memberMapper.findAll().size());

    }

}
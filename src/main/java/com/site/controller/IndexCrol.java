package com.site.controller;

import com.site.mapper.award.AwardsMapper;
import com.site.mapper.project.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wang0 on 2016/9/22.
 */

@Controller

public class IndexCrol {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private AwardsMapper awardsMapper;

//    @RequestMapping("/")
//    public String Index(Model model){
//
////        List<Project> projects = projectMapper.findTopSix();
//        List<Awards> awards = awardsMapper.findTopSix();
////        model.addAttribute("projects",projects);
//        model.addAttribute("awards",awards);
//        return
//
// "index";
//    }

    @RequestMapping("/")
    public String getPersonalPage(){
        return "首页";
    }

}

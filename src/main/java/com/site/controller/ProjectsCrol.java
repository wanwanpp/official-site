package com.site.controller;

import com.site.mapper.project.ProjectMapper;
import com.site.model.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wang0 on 2016/9/22.
 */

@Controller
@RequestMapping("/projects")
public class ProjectsCrol {

    @Autowired
    private ProjectMapper projectMapper;


    @RequestMapping("/")
    private String showProjects(Model model) {
        List<Project> projects = projectMapper.findAll();
        model.addAttribute("projects", projects);
        return "projects";

    }


}

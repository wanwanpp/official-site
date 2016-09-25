package com.site.controller;

import com.site.mapper.award.AwardsMapper;
import com.site.model.award.Awards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wang0 on 2016/9/25.
 */

@Controller
@RequestMapping("/awards")
public class AwardsCrol {

    @Autowired
    private AwardsMapper awardsMapper;

    @RequestMapping("/")
    private String showAwards(Model model){
        List<Awards> awards = awardsMapper.findAll();
        model.addAttribute("awards",awards);
        return "awards";

    }

}

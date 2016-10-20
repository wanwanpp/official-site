package com.site.controller;

import com.site.mapper.login.MemberMapper;
import com.site.model.share.Share;
import com.site.repository.MemberRepo;
import com.site.repository.ShareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wang0 on 2016/9/25.
 */

@Controller
@RequestMapping("/share")
public class ShareCrol {

    @Autowired
    private ShareRepo shareRepo;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepo memberRepo;


    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    public String createShare(@RequestParam String title,
                              @RequestParam String content,
                              @RequestParam String range) throws IOException {
        String name = getCurrentUsername();
        Integer range1 = Integer.valueOf(range);
        Share shareentity = new Share();
        shareentity.setTitle(title);
        shareentity.setContent(content);
        shareentity.setSharepeople(name);
        shareentity.setFanwei(range1);
        if (range1 == 1) {
            shareentity.setGroupid(memberMapper.findByName(name).getGroup());
        }
        shareentity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(shareentity);
        shareRepo.save(shareentity);
        return "redirect:/share";
    }

    @RequestMapping("/ingroup")
    @ResponseBody
    public List<Share> findInGroup(String name) {

        Integer group = memberMapper.findByName(name).getGroup();
        List<Share> shares = shareRepo.findIsInGroup(group);
        return shares;
    }

    @RequestMapping("/inteam")
    @ResponseBody
    public List<Share> findInTeam() {
        List<Share> shares = shareRepo.findIsInTeam();
        return shares;
    }

    @RequestMapping("/showall")
    @ResponseBody
    public List<Share> findAll() {
        String name = getCurrentUsername();
        Integer group = memberRepo.findByName(name).get(0).getGroup();
        List<Share> shareGroup = shareRepo.findIsInGroup(group);
        List<Share> shareTeam = shareRepo.findIsInTeam();
        shareGroup.addAll(shareTeam);
        return shareGroup;
    }
}

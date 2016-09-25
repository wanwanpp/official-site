package com.site.controller.sign;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.model.sign.Buqian;
import com.site.model.sign.Member;
import com.site.model.sign.SignRecords;
import com.site.repository.BuqianRepo;
import com.site.repository.MemberRepo;
import com.site.repository.SignRecordsRepo;
import com.site.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class SignCrol {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private SignRecordsRepo signRecordsRepo;

    @Autowired
    private BuqianRepo buqianRepo;

    @RequestMapping("/name")
    @ResponseBody
    public List<Member> show(Model model) {

        List<Member> members = memberRepo.findAll();
        return members;
    }

    @RequestMapping("/signWork")
    public String show() {
        return "signWork";
    }

    @RequestMapping(value = "/start", produces = "application/text")
    @ResponseBody
    public String sendStart(@RequestBody String name) {

        SignRecords signRecords = new SignRecords(name);
        if (signRecordsRepo.save(signRecords) != null) {
            memberRepo.setIsStart(name);
            return "签到成功";
        } else {
            return "签到失败，请重试";
        }
    }

    @RequestMapping(value = "/end", produces = "application/text")
    @ResponseBody
    public String sendend(@RequestBody String name) {

        Long id = signRecordsRepo.selectDesc(name).get(0).getId();
        Long cometime = signRecordsRepo.selectComeTime(id).getTime();
        Timestamp leaveTime = new Timestamp(System.currentTimeMillis());
        Long totalTime = leaveTime.getTime() - cometime;
        String str_total = String.valueOf(DateUtil.formatdate(totalTime));
        if (signRecordsRepo.setSendEnd(leaveTime, totalTime, str_total, id) == 1) {
            memberRepo.setIsEnd(name);
            return "签退成功";
        } else {
            return "签退失败，请重试";
        }
    }

    @RequestMapping(value = "/buqian", produces = "application/text")
    @ResponseBody
    public String buqian(@RequestBody String string) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = (Map<String, String>) mapper.readValue(string, Map.class);
        String name = map.get("name");
        String number = map.get("number");


        Buqian buqian = new Buqian(name, number + "小时");

        System.out.println(number);
        System.out.println(name);
        SignRecords signRecords = new SignRecords();
        signRecords.setName(name);
        signRecords.setComeTime(new Timestamp(System.currentTimeMillis()));
        signRecords.setLeaveTime(new Timestamp(System.currentTimeMillis()));
        Long totalMill = Long.valueOf(Integer.parseInt(number) * 3600 * 1000);
        signRecords.setTotalMill(totalMill);
        signRecords.setStrTime(String.valueOf(DateUtil.formatdate(totalMill)));

        if (buqianRepo.save(buqian) != null && signRecordsRepo.save(signRecords) != null) {
            return "补签成功";
        } else
            return "补签失败，请重试";
    }
}

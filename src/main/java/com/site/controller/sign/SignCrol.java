package com.site.controller.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.model.login.Member;
import com.site.model.sign.SignRecords;
import com.site.repository.BuqianRepo;
import com.site.repository.MemberRepo;
import com.site.repository.SignRecordsRepo;
import com.site.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/sign")
public class SignCrol {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private SignRecordsRepo signRecordsRepo;

    @Autowired
    private BuqianRepo buqianRepo;

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping("/name")
    @ResponseBody
    public List<Member> show() {

        List<Member> members = memberRepo.findAll();
        return members;
    }

    @RequestMapping("")
    @PreAuthorize("hasAnyAuthority('SIGN','SUPER_ADMIN')")
    public String showpage() {
        return "signWork";
    }

    @RequestMapping(value = "/start", produces = "application/text")
    @ResponseBody
    public String sendStart(@RequestBody String name) {

        SignRecords signRecords = new SignRecords(name);
        if (signRecordsRepo.save(signRecords) != null) {
            memberRepo.setIsStart(name);
            return name + "签到成功";
        } else {
            return name + "签到失败，请重试";
        }
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<SignRecords> getData(@RequestBody String string) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = (Map<String, String>) mapper.readValue(string, Map.class);
        String starts = map.get("starts");
        String ends = map.get("ends");
        String name = getCurrentUsername();
        System.out.println(name);
        long start = new SimpleDateFormat("yyyy-MM-dd").parse(starts).getTime();
        long end = new SimpleDateFormat("yyyy-MM-dd").parse(ends).getTime();

        List<SignRecords> signRecordses = signRecordsRepo.queryByNameTimeDetail(name, new Timestamp(start), new Timestamp(end));
        System.out.println(signRecordses.size());
        if (signRecordses.size() != 0 && signRecordses != null) {
            String total = String.valueOf(DateUtil.formatdate(signRecordsRepo.queryByNameTime(name, new Timestamp(start), new Timestamp(end))));
            SignRecords sign = new SignRecords();
            sign.setStrTime(total);
            signRecordses.add(sign);
        }

        return signRecordses;
    }

    @Scheduled(cron = "0 0 23 0 0 7/4")
    public void autoSignWeekday(){

        List<SignRecords> signRecordses = signRecordsRepo.findByLeaveTimeIsNull();
        StringBuilder names = new StringBuilder();
        for (SignRecords signRecords:signRecordses){
            names=names.append(signRecords.getName()+"  ");
            Timestamp leaveTimeStamp = new Timestamp(System.currentTimeMillis());
            Long totalTime = leaveTimeStamp.getTime() - signRecords.getComeTime().getTime();
            String str_total = String.valueOf(DateUtil.formatdate(totalTime));
            signRecordsRepo.setSendEnd(leaveTimeStamp,totalTime,str_total,signRecords.getId());
            memberRepo.setIsEnd(signRecords.getName());
        }
        log.debug("自动补签成功，此次自动为"+names+signRecordses.size()+"位同学补签");

    }

    @Scheduled(cron = "0 30 23 0 0 5/6")
    public void autoSignWeekend(){

        List<SignRecords> signRecordses = signRecordsRepo.findByLeaveTimeIsNull();
        StringBuilder names = new StringBuilder();
        for (SignRecords signRecords:signRecordses){
            names=names.append(signRecords.getName()+"  ");
            Timestamp leaveTimeStamp = new Timestamp(System.currentTimeMillis());
            Long totalTime = leaveTimeStamp.getTime() - signRecords.getComeTime().getTime();
            String str_total = String.valueOf(DateUtil.formatdate(totalTime));
            signRecordsRepo.setSendEnd(leaveTimeStamp,totalTime,str_total,signRecords.getId());
            memberRepo.setIsEnd(signRecords.getName());
        }
        log.debug("自动补签成功，此次自动为"+names+signRecordses.size()+"位同学补签");

    }

    @RequestMapping(value = "/end", produces = "application/text")
    @ResponseBody
    public String sendend(@RequestBody String name) {

        Long id = signRecordsRepo.selectDesc(name).get(0).getId();
        Long cometime = signRecordsRepo.selectComeTime(id).getTime();
        Timestamp comeTimeStamp = new Timestamp(cometime);
        Timestamp leaveTimeStamp = new Timestamp(System.currentTimeMillis());
        if (leaveTimeStamp.getDate()-comeTimeStamp.getDate()!=0){
            signRecordsRepo.delete(id);
            memberRepo.setIsEnd(name);
            return name+"此次通宵签到无效";
        }
        Long totalTime = leaveTimeStamp.getTime() - cometime;
        if (totalTime > 6 * 60 * 60 * 1000) {
            signRecordsRepo.delete(id);
            memberRepo.setIsEnd(name);
            return name+"签到超过6小时，签到无效";
        } else {
            String str_total = String.valueOf(DateUtil.formatdate(totalTime));
            if (signRecordsRepo.setSendEnd(leaveTimeStamp, totalTime, str_total, id) == 1) {
                memberRepo.setIsEnd(name);
                return name + "签退成功";
            } else {
                return name + "签退失败，请重试";
            }
        }

    }

}

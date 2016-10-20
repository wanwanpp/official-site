package com.site.controller.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.model.sign.Buqian;
import com.site.model.sign.SignRecords;
import com.site.repository.BuqianRepo;
import com.site.repository.SignRecordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wang0 on 2016/10/16.
 */

@Controller
@RequestMapping("/buqian")
public class BuqianCrol {

    @Autowired
    private BuqianRepo buqianRepo;

    @Autowired
    private SignRecordsRepo signRecordsRepo;

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping(value = "/apply ", produces = "application/text")
    @ResponseBody
    public void apply(@RequestBody String string) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = (Map<String, String>) mapper.readValue(string, Map.class);
        String name = getCurrentUsername();
        String number = map.get("number");

        Buqian buqian = new Buqian(name, number + "小时");

        buqianRepo.save(buqian);
//        return "signDetail";
    }

    @RequestMapping("/showReviewPage")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public String showReviewPage(){
        return "reviewBuqian";
    }

    @RequestMapping("/review")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @ResponseBody
    public List<Buqian> review(){
        List<Buqian> buqians = buqianRepo.findByStatusEq0();
        return buqians;
    }

    @RequestMapping("/setBuqianSuccessful")
    @ResponseBody
    public void setBuqianSuccessful(@RequestParam Long id){
        Buqian buqian = buqianRepo.findOne(id);
        buqianRepo.setBuqianSuccessful(buqian.getId());

        SignRecords signRecords = new SignRecords();
        signRecords.setName(buqian.getName());
        signRecords.setComeTime(buqian.getCreateTime());
        signRecords.setLeaveTime(buqian.getCreateTime());
        signRecords.setTotalMill(Long.valueOf(Integer.valueOf(buqian.getDate().substring(0,1))*3600*1000));
        signRecords.setStrTime(buqian.getDate());

        signRecordsRepo.save(signRecords);

    }

    @RequestMapping("/setBuqianFailed")
    @ResponseBody
    public void setBuqianFailed(@RequestParam Long id){
        buqianRepo.setBuqianFailed(id);
    }
}

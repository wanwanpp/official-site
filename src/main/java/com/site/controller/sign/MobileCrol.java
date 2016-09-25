package com.site.controller.sign;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.model.sign.ItemEntity;
import com.site.model.sign.ResponseMessage;
import com.site.model.sign.SignRecords;
import com.site.repository.MemberRepo;
import com.site.repository.SignRecordsRepo;
import com.site.utils.DateUtil;
import com.site.utils.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wang0 on 2016/9/13.
 */
@Controller
@Slf4j
@RequestMapping("/mobile")
public class MobileCrol {

    @Autowired
    private SignRecordsRepo signRecordsRepo;

    @Autowired
    private MemberRepo memberRepo;

    @RequestMapping("/gettoday")
    @ResponseBody
    public long gettoday(@RequestParam(name = "name") String name) {
        Date date = new Date(System.currentTimeMillis());
        Timestamp start = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0, 0);
        Timestamp end = new Timestamp(System.currentTimeMillis());
        Long todayTotal = 0l;
        try {
            todayTotal = signRecordsRepo.gettoday(name, start, end);
        } catch (Exception e) {

        } finally {
            return todayTotal;
        }
    }

 @RequestMapping("/getData")
 @ResponseBody
 public List<SignRecords> getData(@RequestBody String string) throws Exception{

     ObjectMapper mapper = new ObjectMapper();
     Map<String, String> map = (Map<String, String>) mapper.readValue(string, Map.class);
     String name = map.get("name");
     String starts = map.get("starts");
     String ends = map.get("ends");

     long start = new SimpleDateFormat("yyyy-MM-dd").parse(starts).getTime();
     long end = new SimpleDateFormat("yyyy-MM-dd").parse(ends).getTime();

     List<SignRecords> signRecordses = signRecordsRepo.queryByNameTimeDetail(name,new Timestamp(start),new Timestamp(end));
     String total = String.valueOf(DateUtil.formatdate(signRecordsRepo.queryByNameTime(name,new Timestamp(start),new Timestamp(end))));
     SignRecords sign = new SignRecords();
     sign.setStrTime(total);
     return signRecordses;
 }

    @RequestMapping("/todb")
    @ResponseBody
    public ResponseMessage todb(@RequestBody ItemEntity info) {


        Date startdate = new Date(info.getComeTimeStamp());
        Date enddate = new Date(info.getLeaveTimeStamp());
        if (Global.contain(startdate.getHours()) || Global.contain(enddate.getHours()) || enddate.getDay() - startdate.getDay() == 1) {
            return new ResponseMessage(Global.INVALID_SIGN);
        }
        SignRecords signRecords = new SignRecords();
        signRecords.setComeTime(new Timestamp(info.getComeTimeStamp()));
        signRecords.setLeaveTime(new Timestamp(info.getLeaveTimeStamp()));
        signRecords.setTotalMill(info.getLeaveTimeStamp() - info.getComeTimeStamp());
        signRecords.setStrTime(String.valueOf(DateUtil.formatdate(signRecords.getTotalMill())));
        signRecords.setName(info.getName());
        signRecordsRepo.save(signRecords);
        return new ResponseMessage(Global.SIGN_SUCCESS);
    }

}

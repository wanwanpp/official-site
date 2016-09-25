package com.site.controller.sign;


import com.site.model.sign.Member;
import com.site.model.sign.ResponseMessage;
import com.site.repository.MemberRepo;
import com.site.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wang0 on 2016/9/13.
 */
@Controller
public class MemberCrol {

    @Autowired
    private MemberRepo memberRepo;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseMessage reister(@RequestBody Member member){

        if (memberRepo.findByStuId(member.getStuId())==null){
            memberRepo.save(member);
            return new ResponseMessage(Global.REGISTER_SUCCESS);
        }else {
            return new ResponseMessage(Global.HAVE_REGISTERED);
        }
    }




}

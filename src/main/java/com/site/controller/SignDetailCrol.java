package com.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wang0 on 2016/9/28.
 */
@Controller
@RequestMapping("/signDetail")
public class SignDetailCrol {

    @RequestMapping("")
    public String showpage(){
        return "signDetail";
    }

}

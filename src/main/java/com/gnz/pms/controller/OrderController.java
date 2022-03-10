package com.gnz.pms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("save")
    @RequiresRoles("admin")
    public String save(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            System.out.println("保存订单");
        }else{
            System.out.println("无权访问");
        }
        return "redirect:/home.html";
    }
}

package com.gnz.pms.controller;

import com.gnz.pms.service.ISystemLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/syslog/*")
public class SystemLogAction extends BaseController{
    @Resource
    private ISystemLogService systemLogService;

    @RequestMapping("list")
    public  String  listLogs(@RequestParam(value = "kw",defaultValue = "") String kw,
                              @RequestParam(value = "cp",defaultValue = "1") Integer  cp,
                              @RequestParam(value ="ls",defaultValue = "10")Integer ls,
                              Model model, String flag, HttpServletRequest request) throws  Exception{
        //调用业务层的方法查询之后保存到request内置对象中之后再跳转到用户列表显示的页面（user_list.html）
        System.out.println("有没有东西："+request.getSession().getAttribute("user"));
        if(request.getSession().getAttribute("user")!=null){
            model.addAttribute("log_map",systemLogService.findAllSplitLogs(kw,cp,ls));
        }else{
            return "";
        }
        if(flag.equals("log")) {
            return "logininfo/logininfo";
        }
        return null;
    }
    @RequestMapping("remove")
    public  boolean   removeUserLogById(Integer  id,HttpServletRequest request) throws Exception{
        if(super.isAuth("addUserLimit",request))
            return  systemLogService.removeLogById(id);
        return false;
    }

    @Override
    public String getDir() {
        return null;
    }
}

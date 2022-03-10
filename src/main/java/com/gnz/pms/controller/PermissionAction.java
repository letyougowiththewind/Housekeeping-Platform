package com.gnz.pms.controller;
import com.gnz.pms.service.IPermissionService;
import com.gnz.pms.entities.Permission;
import com.gnz.pms.service.IPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
@Controller
@RequestMapping("/permission/*")
public class PermissionAction {
    @Resource
    private IPermissionService permissionService;
    @RequestMapping("list")
    public   String     getPermissions(Integer  uid, String uname, Model  model) throws  Exception{
        //根据用户的编号查询权限信息,并且保存到request内置对象
        model.addAttribute("per",permissionService.findPermissionById(uid));
        System.out.println(permissionService.findPermissionById(uid));
        model.addAttribute("uid",uid);
        model.addAttribute("uname",uname);
        //跳转到add_limitse.html页面（该页面是显示和编辑权限信息的）
        return  "limit/add_limitse";
    }
    @RequestMapping("update")
    @ResponseBody
    public  boolean   modifyPermission(Permission  permission) throws  Exception{
        //调用业务层实现类对象的方法
        return permissionService.editPermission(permission);
    }
}

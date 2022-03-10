package com.gnz.pms.controller;
import com.gnz.pms.entities.Tenant;
import com.gnz.pms.service.ITenantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tenant/*")
public class TenantAction  extends  BaseController{
    @Resource
    private ITenantService  tenantService;
    //增加租户信息
    @RequestMapping("add")
    @ResponseBody
    public  boolean  addTenant(Tenant tenant) throws  Exception{
         return tenantService.createTenant(tenant);
    }
    /**
     * 根据编号删除租户信息
     * @param id  租户编号
     * @param identitypositive 身份证正面照片地址
     * @param identitynegative 身份证反面照片地址
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public boolean  removeTenant(HttpServletRequest request,Integer  id,String identitypositive,String identitynegative) throws  Exception{
        System.out.println("删除的租户的编号是："+id);
        if(tenantService.removeById(id)){
            //删除对应的照片信息
            super.removeFile(request,identitypositive);
            super.removeFile(request,identitynegative);
            return true;
        }
        return false;
    }
    //查询租户信息
    @RequestMapping("list")
    public  String  getTenants(@RequestParam(value = "kw",defaultValue = "")String kw,
                               @RequestParam(value = "cp",defaultValue = "1") Integer  cp,
                               @RequestParam(value = "ls",defaultValue = "10") Integer ls, Model model) throws  Exception{
        model.addAttribute("tenant_map",tenantService.findAllSplit(kw,cp,ls));
        return  "tenants/tenant_list";
    }
    //修改租户信息
    @RequestMapping("update")
    @ResponseBody
    public  boolean   modifyTenant(Tenant tenant) throws  Exception{
        return  tenantService.editTenant(tenant);
    }
    /**
     * 实现上传的方法
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public  String  upload(HttpServletRequest request, MultipartFile  file) throws  Exception{
        return  super.saveFile(request,file);
    }
    @Override
    public String getDir() {
        return "tenant/";
    }
}

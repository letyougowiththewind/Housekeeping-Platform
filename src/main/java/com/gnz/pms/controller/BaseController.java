package com.gnz.pms.controller;
import com.gnz.pms.entities.Permission;
import com.gnz.pms.entities.SysUser;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public abstract class BaseController {
    @Resource
    private MessageSource messageSource;
    /**
     * 根据给定key值读取资源文件中的信息
     * @param key
     * @param args
     * @return
     */
    public String getMsg(String key, String... args) {
        //Locale.getDefault()：取得的是当前系统的语言环境信息
        System.out.println(Locale.getDefault());
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //创建一个期日格式化类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //追加一个自定义的转换器，遇到java.util.Date类型，就使用定义好的SimpleDateFormat类来进行转换（格式化处理）
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, false));//这里的false属性可以为空
    }
    /**
     *  实现权限验证的方法
     * @param opreationName  操作权限的名称，比如说（addUserLimit）
     * @param request 内置对象
     * @return
     */
    public  boolean  isAuth(String opreationName, HttpServletRequest request){
        //从session中取得用户信息
        SysUser  user=(SysUser) request.getSession().getAttribute("user");
        //取得当前用户的权限信息
        Permission  permission=user.getPermission();
        //取得Permission类的Class类对象
        Class  c=permission.getClass();
        Integer  per=0;
        try {
            //使用反射取得对应的权限对应的属性
            Field f = c.getDeclaredField(opreationName);
            f.setAccessible(true);
            per=(Integer) f.get(permission);
        }catch (Exception e){
            e.printStackTrace();
        }
        return   per==1;//如果per==1,表示有权限,最终返回true
    }
    /**
     * 实现上传文件的方法
     * @param req
     * @param photo
     * @return
     * @throws Exception
     */
    public  String  saveFile(HttpServletRequest req, MultipartFile  photo )throws  Exception{
        //图片上传的路径（要保存到服务器的文件夹中）
        String   path=req.getServletContext().getRealPath("/"+this.getDir());
        System.out.println("图片的路径为："+path);
        //生成图片的新名称
        String  fileName= UUID.randomUUID().toString().replace("-","");
        //取得原文件的扩展名
        String  ext=photo.getOriginalFilename().split("\\.")[1];
        //实例化一个File类对象
        File  file=new File(path+fileName+"."+ext);
        if(!file.getParentFile().exists())
            file.mkdirs();
        //保存图片
        photo.transferTo(file);
        //返回图片的相对路径地址
        return  this.getDir()+fileName+"."+ext;
    }
    /**
     * 删除照片信息
     * @param request
     * @param filenNme
     */
    public  void  removeFile(HttpServletRequest request,String  filenNme){
        String  path=request.getServletContext().getRealPath("/"+filenNme);
        System.out.println(path);
        File file=new File(path);
        if (file.exists())
            file.delete();
    }
    /**
     * 让子类指定自己要使用的文件夹名称
     * @return
     */
    public  abstract String  getDir();
}







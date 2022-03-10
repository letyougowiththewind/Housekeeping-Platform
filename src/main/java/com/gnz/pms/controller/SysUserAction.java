package com.gnz.pms.controller;

import com.gnz.pms.annotation.Log;
import com.gnz.pms.entities.SysUser;
import com.gnz.pms.listener.ShiroSessionListener;
import com.gnz.pms.redis.RedisManager;
import com.gnz.pms.service.ISysUserService;
import com.gnz.pms.shiro.OperationType;
import com.gnz.pms.shiro.OperationUnit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.gnz.pms.intercepters.RetryLimitHashedCredentialsMatcher.DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;

@Controller
@RequestMapping("/sysuser/*")
public class SysUserAction extends BaseController{
    @Resource
    private ISysUserService userService;

    @Resource
    private ShiroSessionListener shiroSessionListener;

    public static final String DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX = "shiro:cache:retrylimit:";
    private String keyPrefix = DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;

    @Resource
    private RedisManager redisManager;
    private String getRedisKickoutKey(String username) {
        return this.keyPrefix + username;
    }

    @Log(detail = "登录提交",level = 1,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    @RequestMapping("login")
    public String login(String  nickName, String  pwd,String id,String  rand,  boolean rememberMe,HttpServletRequest  req) throws  Exception{
//        //判断输入的验证码是否正确
//        if(rand==null||rand.equals("")){
//            req.setAttribute("msg","请输入验证码！");
//            return   "login";
//        }
//        if(!rand.equalsIgnoreCase((String)req.getSession().getAttribute("rand"))){
//            //表示验证码不正确
//            req.setAttribute("msg","验证码错误！");
//            return   "login";
//        }
        Subject subject = SecurityUtils.getSubject();
        ModelAndView mv = new ModelAndView();
        Map<String, String> map = new HashMap<>();
           //在这里把remember me放入
            subject.login(new UsernamePasswordToken(nickName,pwd,rememberMe));
            SysUser  user=userService.findLogin(nickName);
            //调用定义好的验证码判断方法
            Map msgMap = msgCode(req,rand);
            if(msgMap.containsKey("gsm")&&user!=null&&user.getStatus().equals("0")){//登陆成功应该跳转到首页
                req.getSession().setAttribute("user",user);
                req.getSession().setAttribute(user.getNickName(),"耿宁泽最帅");
                //使用客户端重定向
                return "redirect:/home.html";
            }else {//登陆失败跳转到login.html
                mv.addObject("msg","该用户已被锁定，请联系管理员！");
                //服务端重定向
                return "redirect:/login.html";
            }
    }
//    @Log(detail = "添加用户",level = 1,operationUnit = OperationUnit.USER,operationType = OperationType.INSERT)
    @RequestMapping("add")
    @ResponseBody
    public boolean adduser(SysUser user, @Valid String remark) throws Exception{
        userService.insertRemark(user.getNickName(),remark);
        //调用业务层的方法，如果增加成功向客户端返回true，否则返回false
        return userService.addUser(user);
    }
    /**
     * 实现昵称的远程验证
     * @param nickName  要验证的昵称
     * @param uid 在添加用户的时候并不需要使用到这个编号，因为此时数据表还不存即将要添加的用户的编号，但是在修改用户昵称的时候
     *            要考虑新的昵称是否被使用了，此时如果没有uid作为判断条件，就会出现自己和自己对比的情况
     * @return
     * @throws Exception
     */
    @PostMapping("checkName")
    @ResponseBody
    public boolean checkNickName(String nickName,Integer  uid) throws  Exception{
        System.out.println(userService.findByNickName(nickName,uid));
        return  userService.findByNickName(nickName,uid);
    }

    /**
     * 实现分页查询
     * @param kw
     * @param cp
     * @param ls
     * @param model
     * @param flag
     * @return
     * @throws Exception
     */
    @RequestMapping("list")
    public  String  listUsers(@RequestParam(value = "kw",defaultValue = "") String kw,
                              @RequestParam(value = "cp",defaultValue = "1") Integer  cp,
                              @RequestParam(value ="ls",defaultValue = "10")Integer ls,
                              Model  model,String flag,HttpServletRequest request) throws  Exception{
        //调用业务层的方法查询之后保存到request内置对象中之后再跳转到用户列表显示的页面（user_list.html）
        if(request.getSession().getAttribute("user")!=null){
            model.addAttribute("user_map",userService.findAllSplit(kw,cp,ls));
            model.addAttribute("count",shiroSessionListener.getSessionCount());
        }else{
            return "";
        }
        if(flag.equals("user")) {
            return "userpages/user_list";
        }else if(flag.equals("limits")) {
            return   "limit/userlimits_list";
        }
        return  null;
    }
    /**
     * 实现注销
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public  String  logout(HttpServletRequest  request){
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        Subject subject = SecurityUtils.getSubject();
        //非常奇怪，下面这里使用String来接收数据会直接跳过循环内容导致报错，暂未知原因
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement().toString();
            request.getSession().removeAttribute(key);
            subject.logout();
        }
        redisManager.del("shiro:cache:kickout:"+user.getNickName());
        redisManager.del("shiro:cache:retrylimit:"+user.getNickName());
        //客户端跳转
        return "redirect:/home.html";
    }

    /**
     * 实现验证码判断
     * @param req
     * @param rand
     * @return
     */
    @RequestMapping("msg_login")
    @ResponseBody
    public Map<String, String> msgCode(HttpServletRequest req,@RequestParam("rand") String rand){
        String system = (String)req.getSession().getAttribute("rand");
        Map<String, String> map = new HashMap<>();
        if(!rand.equalsIgnoreCase((String)req.getSession().getAttribute("rand"))){
            //表示验证码不正确
            req.setAttribute("msg","验证码错误！");
            map.put("msg","falseinput");
        }else{
            map.put("gsm","trueinput");
        }
        return map;
    }

    /**
     * 实现授权码判断
     * @param nickName
     * @return
     */
    @RequestMapping("role_id")
    @ResponseBody
    public Integer roleId(@RequestParam("nickName") String nickName) throws Exception{
       return userService.selectRoleId(nickName);
    }

    /**
     * 实现用户名和密码的判断
     * @param nickName
     * @param pwd
     * @return
     * @throws Exception
     */
    @RequestMapping("user_pwd")
    @ResponseBody
    public String userpwd(@RequestParam("nickName") String nickName,@RequestParam("pwd") String pwd) throws Exception{
        if(userService.selectUserPwd(nickName,pwd).size()!=0){
            Map<String, Object> map = userService.selectUserPwd(nickName,pwd);
            Md5Hash md5Hash = new Md5Hash(pwd.toString(),map.get("salt"),1024);
            if(map.get("pwd").equals(md5Hash.toString())){
//                redisManager.del(getRedisKickoutKey(nickName));
                return "chenggong";
            }
            else{
                AtomicInteger retryCount = (AtomicInteger)redisManager.get(getRedisKickoutKey(nickName));
                if (retryCount == null) {
                    // 如果用户没有登陆过,登陆次数加1 并放入缓存
                     retryCount = new AtomicInteger(0);
                }
                if (retryCount.incrementAndGet() > 3) {
                    // 如果用户登陆失败次数大于3次 抛出锁定用户异常  并修改数据库字段
                    SysUser user = null;
                    try {
                        user = userService.selectByUserName(nickName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (user != null && "0".equals(user.getStatus())){
                        // 数据库字段 默认为 0  就是正常状态 所以 要改为1
                        // 修改数据库的状态字段为锁定
                        user.setStatus("1");
//                        userService.(user);
                    }
                    System.out.println(("锁定用户" + user.getNickName()));
                    redisManager.set(getRedisKickoutKey(nickName), retryCount,30);
                    return "suoding";
//                    // 抛出用户锁定异常
//                    throw new LockedAccountException();
                }
                redisManager.set(getRedisKickoutKey(nickName), retryCount);
                return "shibai";
            }
        }
        return "shibai";
    }

    /**
     * 实现业务概览中图表数据的获取
     * @return
     * @throws Exception
     */
    @RequestMapping("order_day")
    @ResponseBody
    public List getOrderByDays() throws Exception{
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>(2);
        int[] array = new int[]{0,1,2,3,4};
        for(int i=0;i<array.length;i++){
            list2.add(0, array[i]);
//            list2.add(1, 700);
        }
//        for(int j=0;j<list2.size();j++){
//            list1.add(j,list2);
//        }
        return list2;
    }
    /**
     * 实现根据编号删除和更新数据数据
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("remove")
    @ResponseBody
    public  boolean   removeUserByid(Integer  id,HttpServletRequest request) throws Exception{
        if(super.isAuth("addUserLimit",request))
            return  userService.removeById(id);
        return false;
    }
    @RequestMapping("update")
    @ResponseBody
    public  boolean   updateSysUser(SysUser  user,HttpServletRequest request) throws  Exception{
        if(super.isAuth("updateUserLimit",request))
            return  userService.editSysUser(user);
        return  false;
    }


    @Override
    public String getDir() {
        return null;
    }
}

package com.gnz.pms.intercepters;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gnz.pms.entities.SysUser;
import com.gnz.pms.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;


public class RememberAuthenticationFilter extends FormAuthenticationFilter {


    @Resource
    ISysUserService userService;
    /**
     * 这个方法决定了是否能让用户登录
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        //如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true 证明是没登陆直接通过记住我功能进来的
        if(!subject.isAuthenticated() && subject.isRemembered()){

            //获取session看看是不是空的
            Session session = subject.getSession(true);

            //随便拿session的一个属性来看session当前是否是空的，我用userId
            if(session.getAttribute("nickName") == null){
                //如果是空的才初始化，否则每次都要初始化，项目得慢死
                //这边根据前面的前提假设，拿到的是username
                try {
//                    Class sysUserClass = Class.forName(subject.getPrincipal().getClass().toString());
//                    Object obj = sysUserClass.getDeclaredConstructor().newInstance();
//                    Field field = subject.getPrincipal().getClass().getDeclaredField("nickName");
//                    String nickname = (String)field.get(obj);

                    SysUser user = userService.findLogin("gnz");
                    HttpServletRequest req = (HttpServletRequest)request;
                    session.getId();
                    session.setAttribute("user",user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //在这个方法里面做初始化用户上下文的事情，比如通过查询数据库来设置session值
            }
        }
        //这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered() 让它同时也兼容remember这种情况
        return subject.isAuthenticated() || subject.isRemembered();
    }
}

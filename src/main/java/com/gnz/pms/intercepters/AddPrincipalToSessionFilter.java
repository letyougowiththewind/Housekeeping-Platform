package com.gnz.pms.intercepters;

import com.gnz.pms.entities.SysUser;
import com.gnz.pms.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
 
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
 
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import java.io.IOException;
 
/**
 *
 */
public class AddPrincipalToSessionFilter extends OncePerRequestFilter {
    @Resource
    ISysUserService userService;
 
    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        //查询当前用户的信息
        Subject subject = SecurityUtils.getSubject();
        //判断用户是不是通过自动登录进来的
        if (subject.isRemembered()) {
            //如果是，则获取它的手机
            String nickname = subject.getPrincipal().toString();
            if(nickname==null){
                return;
            }
            //根据手机号查询该用户的信息
            SysUser user = null;
            try {
                user = userService.findLogin(nickname);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user == null) return;
            //由于是继承的OncePerRequestFilter，没办法设置session
            //这里发现可以将servletReques强转为子类，所以使用request.getsiion())
            HttpServletRequest request=(HttpServletRequest) servletRequest;
            HttpSession session=request.getSession();
            //当session为空的时候
            if (session.getAttribute("user")==null){
                //把查询到的用户信息设置为session，时效为3600秒
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(-1);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
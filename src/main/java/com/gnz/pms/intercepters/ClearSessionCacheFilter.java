package com.gnz.pms.intercepters;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearSessionCacheFilter implements Filter {
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
 
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String basePath = request.getContextPath();
        request.setAttribute("basePath", basePath);
        // 判断 session 里是否有用户信息
        if (!SecurityUtils.getSubject().isAuthenticated()) {
        	 // 如果是ajax请求响应头会有，x-requested-with
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
        		// 在响应头设置session状态
                response.setHeader("session-status", "timeout");
                return;
            }
        }
        filterChain.doFilter(request, servletResponse);
	}
 
	@Override
	public void destroy() {
		
	}
}
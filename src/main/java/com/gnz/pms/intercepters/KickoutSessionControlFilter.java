package com.gnz.pms.intercepters;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gnz.pms.entities.SysUser;
import com.gnz.pms.redis.RedisManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;




public class KickoutSessionControlFilter extends AccessControlFilter{

	// 踢出后到的地址
	private String kickoutUrl;

	// 踢出之前登录的或者之后登录的用户, 默认踢出之前登录的用户
	private boolean kickoutAfter = false;

	// 同一个帐号最大会话数 默认1
	private int maxSession = 1;

	private SessionManager sessionManager;

	private RedisManager redisManager;

	public static final String DEFAULT_KICKOUT_CACHE_KEY_PREFIX = "shiro:cache:kickout:";

	private String keyPrefix = DEFAULT_KICKOUT_CACHE_KEY_PREFIX;


	public String getKickoutUrl() {
		return kickoutUrl;
	}
	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}
	public boolean isKickoutAfter() {
		return kickoutAfter;
	}
	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}
	public int getMaxSession() {
		return maxSession;
	}
	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}
	public SessionManager getSessionManager() {
		return sessionManager;
	}
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}
	public RedisManager getRedisManager() {
		return redisManager;
	}
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	private String getRedisKickoutKey(String username) {
		return this.keyPrefix + username;
	}

	/**
	 * 是否允许访问，返回 true 表示允许
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}
	/**
	 * 表示访问拒绝时是否自己处理，如果返回 true 表示自己不处理且继续拦截器链执行，返回 false 表示自己已经处理了（比如重定向到另一个页面）。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		Subject subject = getSubject(request, response);
		if(!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}
		Session session = subject.getSession();
		// 传的是userName 这里拿到的就是 userName
		// new SimpleAuthenticationInfo(userName, password, getName());
		SysUser sysuser;
		Object object = subject.getPrincipal();
		if (object instanceof SysUser) {
			sysuser = (SysUser) object;
		} else {
			sysuser = JSON.parseObject(JSON.toJSON(object).toString(), SysUser.class);
		}
		String username = sysuser.getNickName();
		Serializable sessionId = session.getId();
		// 初始化用户的队列放到缓存里
		Deque<Serializable> deque = (Deque<Serializable>) redisManager.get(getRedisKickoutKey(username));
		if(deque == null) {
			deque = new LinkedList<Serializable>();
		}
		// 如果队列里没有此sessionId，且用户没有被踢出；放入队列
		if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
		}

		// 如果队列里的sessionId数超出最大会话数，开始踢人
		while(deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			// 如果踢出后者
			if(kickoutAfter) {
				kickoutSessionId=deque.getFirst();
				kickoutSessionId = deque.removeFirst();
			} else {
				// 否则踢出前者
				kickoutSessionId = deque.removeLast();
			}
			try {
				Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
				if(kickoutSession != null) {
					// 设置会话的 kickout 属性表示踢出了
					kickoutSession.setAttribute("kickout", true);
				}
			} catch (Exception e) {//ignore exception
				e.printStackTrace();
			}
		}
		redisManager.set(getRedisKickoutKey(username), deque);
		// 如果被踢出了，直接退出，重定向到踢出后的地址
		if (session.getAttribute("kickout") != null) {
			// 会话被踢出了
			try {
				subject.logout();
			} catch (Exception e) {

			}
			// WebUtils.issueRedirect(request, response, kickoutUrl);
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			if (isAjax(request)) {
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.setContentType("application/json");
				httpServletResponse.setHeader("session-status", "two-user");
			} else {
				httpServletResponse.sendRedirect(kickoutUrl);
			}
			return false;
		}
		return true;
	}
	private boolean isAjax(ServletRequest request){
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if("XMLHttpRequest".equalsIgnoreCase(header)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
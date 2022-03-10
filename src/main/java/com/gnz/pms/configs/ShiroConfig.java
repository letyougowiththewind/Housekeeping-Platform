package com.gnz.pms.configs;

import java.util.*;

import javax.servlet.Filter;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.gnz.pms.intercepters.RememberAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import com.gnz.pms.redis.RedisCacheManager;
import com.gnz.pms.redis.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gnz.pms.intercepters.ClearSessionCacheFilter;
import com.gnz.pms.intercepters.KickoutSessionControlFilter;
import com.gnz.pms.intercepters.RetryLimitHashedCredentialsMatcher;
import com.gnz.pms.redis.RedisManager;
import com.gnz.pms.listener.ShiroSessionListener;
import com.gnz.pms.shiro.CustomerRealm;


/**
 * 整合Shiro框架相关的配置类
 */
@Configuration
public class ShiroConfig {

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //用于thymeleaf模板使用shiro标签
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public FilterRegistrationBean loginFilter2Registration(RememberAuthenticationFilter rememberAuthenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(rememberAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }
    //创建shiroFilter，负责拦截所有请求
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager defaultWebSecurityManager){
        //Shiro的核心安全接口，这个属性是必需的
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //把自定义过滤器添加进过滤器中
        Map<String, Filter> filters = new HashMap<>();
        filters.put("addPrincipal",rememberAuthenticationFilter());
        filters.put("kickout",kickoutSessionControlFilter());
//        filters.put("clearSession",clearSessionCacheFilter());
        shiroFilterFactoryBean.setFilters(filters);
//        loadShiroFilterChain(shiroFilterFactoryBean);
        //配置系统的受限资源和公共资源
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/**/*.js","anon");
        map.put("/**/*.png","anon");
        map.put("/**/*.jpg","anon");
        map.put("/**/*.css","anon");
        map.put("js/login/login.js","anon");
        map.put("/sysuser/login","anon");
        map.put("/login.html","anon");
        map.put("/druid/**","anon");
        map.put("/imgCode","anon");
        map.put("/sysuser/msg_login","anon");
        map.put("/sysuser/role_id","anon");
        map.put("/sysuser/user_pwd","anon");
        map.put("/sysuser/order_day","anon");
        map.put("/sellContract/select_order_day","anon");
        map.put("/sellContract/echartsView","anon");
        map.put("/calendar.html","authc");
//        map.put("/**","authc");
        map.put("/**","kickout,addPrincipal");
        //配置认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/home.html");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //创建安全管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        manager.setRealm(realm());
        // 将 CookieRememberMeManager 注入到 SecurityManager 中，否则不会生效
        manager.setRememberMeManager(rememberMeManager());
        // 将 sessionManager 注入到 SecurityManager 中，否则不会生效
        manager.setSessionManager(sessionManager());
        // 将 RedisCacheManager 注入到 SecurityManager 中，否则不会生效
        manager.setCacheManager(redisCacheManager());
        return manager;
    }
    @Bean
    //创建自定义realm
    public CustomerRealm realm(){
        CustomerRealm realm = new CustomerRealm();
        //修改默认的凭证匹配器，默认是使用equ比较
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        //开启缓存管理
        realm.setCachingEnabled(true);//开启全局缓存
        realm.setAuthenticationCachingEnabled(true);//认证缓存
        realm.setAuthenticationCacheName("authenticationCache");
        realm.setAuthorizationCachingEnabled(true);//开启授权缓存
        realm.setAuthorizationCacheName("authorizationCache");
        return realm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
        retryLimitHashedCredentialsMatcher.setRedisManager(redisManager());
        // 散列算法:这里使用MD5算法;
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        retryLimitHashedCredentialsMatcher.setHashIterations(1024);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
//        retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return retryLimitHashedCredentialsMatcher;
    }
    @Bean
    public SimpleCookie rememberMeCookie(){
        // 这个参数是 cookie 的名称，叫什么都行,我这块取名 rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // setcookie 的 httponly 属性如果设为 true 的话，会增加对 xss 防护的安全系数，
        // 只能通过http访问，javascript无法访问，防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        // 记住我 cookie 生效时间30天 ,单位是秒
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * FormAuthenticationFilter 过滤器 过滤记住我
     * @return
     */
    @Bean
    public RememberAuthenticationFilter rememberAuthenticationFilter(){
        RememberAuthenticationFilter rememberAuthenticationFilter = new RememberAuthenticationFilter();
        // 对应 rememberMeCookie() 方法中的 name
        rememberAuthenticationFilter.setRememberMeParam("rememberMe");
        return rememberAuthenticationFilter;
    }

    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // redis中针对不同用户缓存
        redisCacheManager.setPrincipalIdFieldName("uid");
        // 用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    /**
     * 让某个实例的某个方法的返回值注入为Bean的实例
     * Spring静态注入
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }
    /**
     * 配置session监听
     * @return
     */
    @Bean("sessionListener")
    public ShiroSessionListener sessionListener(){
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        return sessionListener;
    }
    /**
     * 配置会话ID生成器
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }
    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        // session在redis中的保存时间,最好大于session会话超时时间
        redisSessionDAO.setExpire(12000);
        return redisSessionDAO;
    }
    /**
     * 配置保存sessionId的cookie
     * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理也需要自己的cookie
     * @return
     */
    @Bean("sessionIdCookie")
    public SimpleCookie sessionIdCookie(){
        // 这个参数是cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        // setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        // setcookie()的第七个参数
        // 设为true后，只能通过http访问，javascript无法访问
        // 防止xss读取cookie
        simpleCookie.setHttpOnly(false);
        simpleCookie.setPath("/");
        // maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }
    /**
     * 配置会话管理器，设定会话超时及保存
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager() {

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 为了解决输入网址地址栏出现 jsessionid 的问题
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        // 配置监听
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setCacheManager(redisCacheManager());

        // 全局会话超时时间（单位毫秒），默认30分钟  暂时设置为30秒钟 用来测试
//         sessionManager.setGlobalSessionTimeout(10000);
//         sessionManager.setGlobalSessionTimeout(30000);
        sessionManager.setGlobalSessionTimeout(1800000);
        // 是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        // 是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        // 设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        // 暂时设置为 5秒 用来测试
        sessionManager.setSessionValidationInterval(3600000);
//             sessionManager.setSessionValidationInterval(5000);
        return sessionManager;
    }
//    /**
//     * 校验当前缓存是否失效的拦截器
//     *
//     * */
//    @Bean
//    public ClearSessionCacheFilter clearSessionCacheFilter() {
//        ClearSessionCacheFilter clearSessionCacheFilter = new ClearSessionCacheFilter();
//        return clearSessionCacheFilter;
//    }
    /**
     * 并发登录控制
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        // 用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        kickoutSessionControlFilter.setRedisManager(redisManager());
        // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
        kickoutSessionControlFilter.setKickoutAfter(false);
        // 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        // 被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/login");
        return kickoutSessionControlFilter;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("192.168.142.134:6379");
//        redisManager.setDatabase(0);
//        redisManager.setPassword("xxxx");
        return redisManager;
    }
}

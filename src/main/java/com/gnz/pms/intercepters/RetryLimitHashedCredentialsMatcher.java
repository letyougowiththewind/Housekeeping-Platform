package com.gnz.pms.intercepters;

import java.util.concurrent.atomic.AtomicInteger;

import com.gnz.pms.entities.SysUser;
import com.gnz.pms.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gnz.pms.redis.RedisManager;
 
/**
 * 
 * 登陆次数限制
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
 
	private static final Logger logger = Logger.getLogger(RetryLimitHashedCredentialsMatcher.class);
	
	public static final String DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX = "shiro:cache:retrylimit:";
    private String keyPrefix = DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;
 
	
    @Autowired
    private ISysUserService userService;
    
    private RedisManager redisManager;
 
    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }
 
    private String getRedisKickoutKey(String username) {
        return this.keyPrefix + username;
    }
    
 
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
 
        // 获取登录用户的用户名
        String username = (String)token.getPrincipal();
        // 获取用户登录次数
        AtomicInteger retryCount = (AtomicInteger)redisManager.get(getRedisKickoutKey(username));
        if (retryCount == null) {
            // 如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > 3) {
            // 如果用户登陆失败次数大于3次 抛出锁定用户异常  并修改数据库字段
            SysUser user = null;
            try {
                user = userService.selectByUserName(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user != null && "0".equals(user.getStatus())){
                // 数据库字段 默认为 0  就是正常状态 所以 要改为1
                // 修改数据库的状态字段为锁定
                user.setStatus("1");
//                userService.(user);
            }
            logger.info("锁定用户" + user.getNickName());
            // 抛出用户锁定异常
            throw new LockedAccountException();
        }
        // 判断用户账号和密码是否正确
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // 如果正确,从缓存中将用户登录计数 清除
            redisManager.del(getRedisKickoutKey(username));
        }else {
            redisManager.set(getRedisKickoutKey(username), retryCount);
        }
        return matches;
    }
 
    /**
     * 根据用户名 解锁用户
     * @param username
     * @return
     */
    public void unlockAccount(String username) throws Exception {
        SysUser user = userService.selectByUserName(username);
        if (user != null){
            // 修改数据库的状态字段为解锁
            user.setStatus("0");
//            userService.updateByPrimaryKey(user);
            redisManager.del(getRedisKickoutKey(username));
        }
    }
}
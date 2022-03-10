package com.gnz.pms.shiro;

import com.gnz.pms.entities.Perms;
import com.gnz.pms.entities.Role;
import com.gnz.pms.entities.SysUser;
import com.gnz.pms.service.ISysUserService;
import com.gnz.pms.service.impl.SysUserServiceImpl;
import com.gnz.pms.utils.ApplicationContextUtil;
import com.gnz.pms.utils.MyByteSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    private ISysUserService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取权限信息
        Subject subject = SecurityUtils.getSubject();
        //根据主身份信息获取角色和权限信息
        SysUser sysUser = (SysUser)subject.getPrincipal();
        System.out.println("为用户"+subject+"调用权限验证：");
        List<SysUser> user = service.findRolesByUsername(sysUser.getNickName());
        //授权角色信息
        if(!CollectionUtils.isEmpty(user)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            try {
                //不确定是什么原因导致权限可能会生成一个空值"", 会报错,所以将空值删除
                if (simpleAuthorizationInfo != null && simpleAuthorizationInfo.getStringPermissions() != null) {
                    Set<String> permissions = simpleAuthorizationInfo.getStringPermissions();
                    for (String permission : permissions) {
                        if (StringUtils.isEmpty(permission)) {
                            permissions.remove(permission);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("移除空值权限出错---"+e.getMessage());
            }
            //两次循环实现取出保存在user里的roles的name属性
            user.forEach(role -> {
                List<Role> list = role.getRoles();
                System.out.println("------------->"+role.getRoles());
                for (int i = 0; i < list.size(); i++) {
                    simpleAuthorizationInfo.addRole(list.get(i).getName());
                    //权限信息
                    List<Perms> perms = service.findPermsByRoleId(role.getId());
                    if(!CollectionUtils.isEmpty(perms)){
                        perms.forEach(perm->{
                            simpleAuthorizationInfo.addStringPermission(perm.getName());
                            System.out.println("=================="+perm.getName());
                        });
                    }
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println("当前用户信息为："+principal);
        try {
            SysUser sysUser = service.findLogin(principal);
            return new SimpleAuthenticationInfo(sysUser,sysUser.getPwd(), new MyByteSource(sysUser.getSalt()),this.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 重写方法,清除当前用户的授权缓存
     * @param principal
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principal) {
        super.clearCachedAuthorizationInfo(principal);
    }
    /**
     * 重写方法，清除当前用户的认证缓存
     * @param principal
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principal) {
        super.clearCachedAuthenticationInfo(principal);
    }

    /**
     *  重写方法，清除当前用户的认证缓存和授权缓存
     * */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有用户的授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有用户的认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有用户的认证缓存和授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}

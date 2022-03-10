package com.gnz.pms.dao;
import com.gnz.pms.entities.Perms;
import com.gnz.pms.entities.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISysUserDAO extends IBaseDAO<Integer,SysUser>{
    /**
     * 实现登陆操作，该方法是子接口特有的方法
     * @param nickName  用户名
     * @return  登陆成功返回对应的对象 否则返回null
     * @throws Exception
     */
    public SysUser selectLogin(String nickName) throws Exception;

    /**
     * 实现远程昵称验证的方法
     * @param nickName  你要使用的昵称
     * @param uid  用户的编号
     * @return
     * @throws Exception
     */
    public SysUser selectNickName(String nickName,Integer uid) throws Exception;

    //根据用户名查询所有角色
    public List<SysUser> findRolesByUsername(String nickName);

    //根据角色id查询权限集合
    public List<Perms> findPermsByRoleId(String id);

    //登录界面授权码相关实现
    public Integer selectRoleId(String nickName) throws Exception;

    //登录界面用户名和密码校验
    public SysUser selectUserPwd(String nickName,String pwd) throws Exception;

    public SysUser selectByUserName(String username) throws Exception;

    //新增用户时添加备注
    public void insertRemark(String nickname, String remark) throws Exception;
}

package com.gnz.pms.service;

import com.gnz.pms.entities.Perms;
import com.gnz.pms.entities.SysUser;

import java.util.List;
import java.util.Map;

public interface ISysUserService {

    /**
     * 调用dao层的selectLogin()方法实现登录
     * @param nickName
     * @return
     * @throws Exception
     */
    public SysUser findLogin(String nickName) throws Exception;

    /**
     * 添加用户的方法，调用dao层的insert()方法
     * @param user
     * @return
     * @throws Exception
     */
    public boolean addUser(SysUser user) throws Exception;

    /**
     * 远程验证昵称的方法，调用dao层的selectNickName()方法
     * @param nickName 即将使用的昵称
     * @param uid 用户编号
     * @return 如果已经使用了返回false，此时客户端就不能再填写当前昵称了，否则返回false，客户端可以继续使用当前昵称
     * @throws Exception
     */
    public boolean findByNickName(String nickName,Integer uid) throws Exception;

    /**
     * 实现模糊分页查询，调用dao层的如下两个方法：
     * selectAllSplit()，取得用户列表集合
     * selectCount()，取得查询到的数据量
     * @param kw 模糊查询的关键字
     * @param cp 当前页
     * @param ls 每页显示的数量
     * @return  返回保存了查询到的数据的map集合
     * @throws Exception
     */
    public Map<String,Object> findAllSplit(String kw,Integer cp,Integer ls) throws Exception;

    /**
     * 根据编号删除用户信息
     * @param uid 要删除的用户编号
     * @return
     * @throws Exception
     */
    public boolean removeById(Integer uid) throws Exception;

    /**
     * 实现用户信息的修改（根据编号确定要修改的是哪一条数据）
     * @param user
     * @return
     * @throws Exception
     */
    public boolean editSysUser(SysUser user) throws Exception;

    //根据用户名查询所有角色
    public List<SysUser> findRolesByUsername(String nickName);

    //根据角色id查询权限集合
    public List<Perms> findPermsByRoleId(String id);

    //登录界面授权码相关实现
    public Integer selectRoleId(String nickName) throws Exception;

    //登录界面用户名和密码校验
    public Map<String, Object> selectUserPwd(String nickName,String pwd) throws Exception;

    public SysUser selectByUserName(String username) throws Exception;

    //根据年份查询当前时间
    public List selectTime(String year) throws Exception;

    //新增用户时添加备注
    public void insertRemark(String nickname, String remark) throws Exception;
}
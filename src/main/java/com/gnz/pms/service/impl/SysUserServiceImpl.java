package com.gnz.pms.service.impl;

import com.gnz.pms.dao.IPermissionDAO;
import com.gnz.pms.dao.ISysUserDAO;
import com.gnz.pms.entities.*;
import com.gnz.pms.service.ISysUserService;
import com.gnz.pms.utils.Salt;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUser")
@Transactional//保证事务的一致性
public class SysUserServiceImpl implements ISysUserService {
    @Resource
    private ISysUserDAO userDAO;
    @Resource
    private IPermissionDAO permissionDAO;

    @Override
    public SysUser findLogin(String nickname) throws Exception {
        //查询用户的基本信息，之后再根据查询到的用户的编号查询权限信息
        SysUser  user=userDAO.selectLogin(nickname);
        //将查询到的权限信息保存到SysUser类对象中返回
        if(user!=null)
            user.setPermission(permissionDAO.selectById(user.getUid()));
        return  user;
    }

    @Override
    @Transactional
    public boolean addUser(SysUser user) throws Exception {
        //增加了新的用户之后要为其分配对应的默认权限（但是要取得自增长的主键值）
        //先插入用户信息，自增长的主键值就保存到了user类中的uid属性中
        //生成随机盐
        String salt = Salt.getSalt(4);
        //将随机盐保存到数据库
        user.setSalt(salt);
        //明文密码进行MD5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPwd(),salt,1024);
        user.setPwd(md5Hash.toHex());
        //插入用户时默认设置状态为0和识别码
        user.setStatus("0");
        user.setId("2");
        userDAO.insert(user);
        //创建表示权限的对象
        Permission permission=new Permission(user.getUid(),0,0,0,0);
        //分配权限（将权限信息插入t_limits数据表）
        int  row=permissionDAO.insert(permission);
        return  row>0;
    }

    @Override
    public boolean findByNickName(String nickName, Integer uid) throws Exception {
        return userDAO.selectNickName(nickName,uid)==null;//如果为null则表示数据表中不存在即将使用的昵称
    }

    @Override
    public Map<String, Object> findAllSplit(String kw, Integer cp, Integer ls) throws Exception {
        Map<String,Object> map=new HashMap<>();
        //将查询到的用户集合保存到map集合中
        map.put("list",userDAO.selectSplitAll("%"+kw+"%",(cp-1)*ls,ls));
        //统计数据量
        int  count =userDAO.selectCount("%"+kw+"%");
        //计算出总页数并且保存到map集合中
        map.put("allPages",count/ls+(count%ls==0?0:1));
        //将当前页、每页显示的数据量、模糊查询的关键字、总的数据量保存到map集合中
        map.put("cp",cp);
        map.put("ls",ls);
        map.put("kw",kw);
        map.put("count",count);
        return map;  //返回的是map集合
    }

    @Override
    public boolean removeById(Integer uid) throws Exception {
        return   userDAO.deleteById(uid)>0;
    }

    @Override
    public boolean editSysUser(SysUser user) throws Exception {
        return userDAO.update(user)>0;
    }

    @Override
    public List<SysUser> findRolesByUsername(String nickName) {
        return userDAO.findRolesByUsername(nickName);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userDAO.findPermsByRoleId(id);
    }

    @Override
    public Integer selectRoleId(String nickName) throws Exception {
        return userDAO.selectRoleId(nickName);
    }

    @Override
    public Map<String, Object> selectUserPwd(String nickName, String pwd) throws Exception {
        Map<String, Object> map = new HashMap<>();
        SysUser user = userDAO.selectUserPwd(nickName, pwd);
        if(user!=null){
            map.put("salt",userDAO.selectUserPwd(nickName, pwd).getSalt());
            map.put("pwd",userDAO.selectUserPwd(nickName, pwd).getPwd());
            return map;
        }else{
            return map;
        }
    }

    @Override
    public SysUser selectByUserName(String username) throws Exception {
        return userDAO.selectByUserName(username);
    }

    @Override
    public List selectTime(String year) throws Exception {
        return null;
    }

    @Override
    public void insertRemark(String nickname, String remark) throws Exception {
        userDAO.insertRemark(nickname, remark);
    }
}

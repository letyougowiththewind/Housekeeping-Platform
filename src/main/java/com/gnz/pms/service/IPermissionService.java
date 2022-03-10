package com.gnz.pms.service;

import com.gnz.pms.entities.Permission;

public interface IPermissionService {
    /**
     * 实现权限信息的查询
     * @param uid  要查询的用户的编号
     * @return
     * @throws Exception
     */
    public Permission  findPermissionById(Integer  uid) throws  Exception;
    /**
     * 编辑用户的权限信息
     * @param permission
     * @return
     * @throws Exception
     */
    public boolean  editPermission(Permission  permission) throws  Exception;
}

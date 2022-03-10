package com.gnz.pms.service.impl;

import com.gnz.pms.dao.IPermissionDAO;
import com.gnz.pms.entities.Permission;
import com.gnz.pms.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Resource
    private IPermissionDAO  permissionDAO;
    @Override
    public Permission findPermissionById(Integer uid) throws Exception {
        return  permissionDAO.selectById(uid);
    }
    @Override
    public boolean editPermission(Permission permission) throws Exception {
        return permissionDAO.update(permission)>0;
    }
}

package com.gnz.pms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permission implements Serializable {
    //定义出对应数据表中的字段对应的属性信息
    private  Integer  uid;//用户编号
    private  Integer  addUserLimit;//添加用户的权限
    private  Integer  updateUserLimit;//修改用户的权限
    private  Integer  addowenerLimit;//增加业主的权限
    private  Integer updateOwnerLimit;//修改业主信息的权限

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAddUserLimit() {
        return addUserLimit==null ? 0:addUserLimit;
    }

    public void setAddUserLimit(Integer addUserLimit) {
        this.addUserLimit = addUserLimit;
    }

    public Integer getUpdateUserLimit() {
        return updateUserLimit==null ? 0:updateUserLimit;
    }

    public void setUpdateUserLimit(Integer updateUserLimit) {
        this.updateUserLimit = updateUserLimit;
    }

    public Integer getAddowenerLimit() {
        return addowenerLimit==null ? 0:addowenerLimit;
    }

    public void setAddowenerLimit(Integer addowenerLimit) {
        this.addowenerLimit = addowenerLimit;
    }

    public Integer getUpdateOwnerLimit() {
        return updateOwnerLimit==null ? 0:updateOwnerLimit;
    }

    public void setUpdateOwnerLimit(Integer updateOwnerLimit) {
        this.updateOwnerLimit = updateOwnerLimit;
    }
}

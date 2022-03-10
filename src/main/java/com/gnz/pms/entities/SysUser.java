package com.gnz.pms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    private Integer uid;
    private String name;
    private String nickName;
    private Integer delstatus;
    private String pwd;
    private String phone;
    private String email;
    private String img;
    private String qq;
    private Date regtime;
    private Permission permission;
    private String salt;
    private String id;
    private String status;
    //定义角色集合
    private List<Role> roles;
}

package com.gnz.pms.entities;
import java.io.Serializable;
import java.util.Date;

public class Properietor implements Serializable {
    //属性要和数据表的字段对应起来
    // 暂时没有不用序列化的成员变量
    private Integer id;// 业主id
    private String proprietorName;// 业主名字
    private Integer gender;// 性别：0：女 1：男
    private Date brithday;// 生日
    private String IDNum;// 身份证号
    private Integer status = 1;// 状态 （1表示正常，0表示停用）默认为1
    private  String phone;// 电话
    private String belongCompany;// 所属公司
    private String address;// 联系地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

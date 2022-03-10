package com.gnz.pms.entities;
import java.io.Serializable;
public class Developer  implements Serializable {
    private Integer id;
    private String developerName;
    private String dCompanyName;
    private String lawPerson;
    private String linkman;
    private String phone;
    private String dAddr;
    private String remark;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getdCompanyName() {
        return dCompanyName;
    }

    public void setdCompanyName(String dCompanyName) {
        this.dCompanyName = dCompanyName;
    }

    public String getLawPerson() {
        return lawPerson;
    }

    public void setLawPerson(String lawPerson) {
        this.lawPerson = lawPerson;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getdAddr() {
        return dAddr;
    }

    public void setdAddr(String dAddr) {
        this.dAddr = dAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

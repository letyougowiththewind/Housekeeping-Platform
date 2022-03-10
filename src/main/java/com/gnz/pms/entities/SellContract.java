package com.gnz.pms.entities;
import java.io.Serializable;
import java.util.Date;

public class SellContract  implements Serializable {
    //里面的属性要和t_SellContract 数据 表中的字段对应，同时还需要增加一些其他字段
    //比如说  业主性别  和  身份证号码  以及 业主的编号（标准严谨的操作是：在插入数据的时候要先增加对应的业主，之后获取业主的自增长主键值 来作为合同中的业主编号）
    private Integer id;
    private String residence_info;
    private Integer room_id;
    private String sellcontract_id;// 该编号在程序中按照一定的算法生成（签约日期+合同的数据库中合同的编号）
    private Date signtime;
    private String proprietorName;// 业主名称proprietorName
    private String phone;
    private Double contract_value;// 合同金额

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    private String payment_method;// 支付方式
    private String IDNum;// 身份证号码
    private String identitypositive;// 省份证正面
    private String address;
    private String gender;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getResidence_info() {
        return residence_info;
    }

    public void setResidence_info(String residence_info) {
        this.residence_info = residence_info;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getSellcontract_id() {
        return sellcontract_id;
    }

    public void setSellcontract_id(String sellcontract_id) {
        this.sellcontract_id = sellcontract_id;
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getContract_value() {
        return contract_value;
    }

    public void setContract_value(Double contract_value) {
        this.contract_value = contract_value;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getIdentitypositive() {
        return identitypositive;
    }

    public void setIdentitypositive(String identitypositive) {
        this.identitypositive = identitypositive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

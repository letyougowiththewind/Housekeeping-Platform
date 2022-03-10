package com.gnz.pms.entities;
import java.io.Serializable;
import java.util.Date;

public class Contract  implements Serializable {
    private  Integer  id;  //合同主键值
    private  Integer  tenant_id ;  //租户的编号
    private  Integer pcharge_id ;  //物业用途编号
    private  Integer purpose_id ; // 用途编号
    private  Date    signtime;  //签约日期
    private  Date    starttime;//起租日期
    private  Date    endtime; //结束日期
    private  Double  monthfee;  //月租金
    private  Double  deposit;  //押金
    private  String  remarks;  //备注
    private  Integer room_id;  //房间的编号
    private  Integer status;   //出租状态
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTenant_id() {
        return tenant_id;
    }
    public void setTenant_id(Integer tenant_id) {
        this.tenant_id = tenant_id;
    }
    public Integer getPcharge_id() {
        return pcharge_id;
    }
    public void setPcharge_id(Integer pcharge_id) {
        this.pcharge_id = pcharge_id;
    }
    public Integer getPurpose_id() {
        return purpose_id;
    }
    public void setPurpose_id(Integer purpose_id) {
        this.purpose_id = purpose_id;
    }
    public Date getSigntime() {
        return signtime;
    }
    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }
    public Date getStarttime() {
        return starttime;
    }
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return endtime;
    }
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    public Double getMonthfee() {
        return monthfee;
    }
    public void setMonthfee(Double monthfee) {
        this.monthfee = monthfee;
    }
    public Double getDeposit() {
        return deposit;
    }
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Integer getRoom_id() {
        return room_id;
    }
    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }
    @Override
    public String toString() {
        return "Contract [id=" + id + ", tenant_id=" + tenant_id + ", pcharge_id=" + pcharge_id + ", purpose_id="
                + purpose_id + ", signtime=" + signtime + ", starttime=" + starttime + ", endtime=" + endtime
                + ", monthfee=" + monthfee + ", deposit=" + deposit + ", remarks=" + remarks + ", room_id=" + room_id
                + ", status=" + status + "]";
    }
}

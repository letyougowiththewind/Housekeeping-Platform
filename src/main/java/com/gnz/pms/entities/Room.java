package com.gnz.pms.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Room  implements Serializable {
    private  Integer   id;
    private  Integer   property;//物业类型（每种类型收费是不一样）
    private  Integer roomNum    ;//房间编号
    @JSONField(name="name")
    private  String  roomName ;//房间名
    private  String houseType;//房间户型
    private  Double  roomArea ;//房间面积
    private  Double   useArea ;//可用面积
    private  String  toward;//朝向
    private  String  decoration;//装修类型
    private  Integer  statusTenant;//出租状态： 0表示未出租  1表示出租 2表示出售（可以自己规定）
    private  Integer floor_id ;//当前房间所属的楼层编号
    private  Integer developer_id;//开发商编号
    private  Integer properietor_id;//房间的用途编号
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getProperty() {
        return property;
    }
    public void setProperty(Integer property) {
        this.property = property;
    }
    public Integer getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getHouseType() {
        return houseType;
    }
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    public Double getRoomArea() {
        return roomArea;
    }
    public void setRoomArea(Double roomArea) {
        this.roomArea = roomArea;
    }
    public Double getUseArea() {
        return useArea;
    }
    public void setUseArea(Double useArea) {
        this.useArea = useArea;
    }
    public String getToward() {
        return toward;
    }
    public void setToward(String toward) {
        this.toward = toward;
    }
    public String getDecoration() {
        return decoration;
    }
    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }
    public Integer getStatusTenant() {
        return statusTenant;
    }
    public void setStatusTenant(Integer statusTenant) {
        this.statusTenant = statusTenant;
    }
    public Integer getFloor_id() {
        return floor_id;
    }
    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }
    public Integer getDeveloper_id() {
        return developer_id;
    }
    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }
    public Integer getProperietor_id() {
        return properietor_id;
    }
    public void setProperietor_id(Integer properietor_id) {
        this.properietor_id = properietor_id;
    }
    @Override
    public String toString() {
        return "Room [id=" + id + ", property=" + property + ", roomNum=" + roomNum + ", roomName=" + roomName
                + ", houseType=" + houseType + ", roomArea=" + roomArea + ", useArea=" + useArea + ", toward=" + toward
                + ", decoration=" + decoration + ", statusTenant=" + statusTenant + ", floor_id=" + floor_id
                + ", developer_id=" + developer_id + ", properietor_id=" + properietor_id + "]";
    }
}

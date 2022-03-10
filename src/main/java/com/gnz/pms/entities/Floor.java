package com.gnz.pms.entities;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
public class Floor  implements Serializable {
    private Integer id;
    private Integer floorNum; //楼层的序号
    @JSONField(name="name")//在将对象转换成json的时候会把floorName 替换成name
    private String floorName;   //楼层名称
    private Double floorArea; // 楼层面积
    private Double publicArea; // 公共区域面积
    private Integer roomCount; // 当前楼层的房间数量
    private Integer build_id;// 当前楼层所属的楼栋编号
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Double floorArea) {
        this.floorArea = floorArea;
    }

    public Double getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(Double publicArea) {
        this.publicArea = publicArea;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getBuild_id() {
        return build_id;
    }

    public void setBuild_id(Integer build_id) {
        this.build_id = build_id;
    }

    @Override
    public String toString() {
        return "Floor [id=" + id + ", floorNum=" + floorNum + ", floorName=" + floorName + ", floorArea=" + floorArea
                + ", publicArea=" + publicArea + ", roomCount=" + roomCount + ", build_id=" + build_id + "]";
    }
}

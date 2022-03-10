package com.gnz.pms.entities;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
public class Building  implements Serializable {
    private Integer id; // 楼栋的主键值
    private Integer buildNum; // 楼栋在小区中的序号
    @JSONField(name="name")  //buildName 属性换为name
    private String buildName;// 楼栋名称
    private Integer floorCount;// 当前楼栋的楼层数
    private Integer community_id;// 当前楼栋所属的小区编号
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public Integer getBuildNum() {
        return buildNum;
    }
    public void setBuildNum(Integer buildNum) {
        this.buildNum = buildNum;
    }
    public String getBuildName() {
        return buildName;
    }
    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }
    public Integer getFloorCount() {
        return floorCount;
    }
    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }
    public Integer getCommunity_id() {
        return community_id;
    }
    public void setCommunity_id(Integer community_id) {
        this.community_id = community_id;
    }
    @Override
    public String toString() {
        return "Building [id=" + id + ", buildNum=" + buildNum + ", buildName=" + buildName + ", floorCount="
                + floorCount + ", community_id=" + community_id + "]";
    }
}

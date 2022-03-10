package com.gnz.pms.entities;
import java.io.Serializable;
public class Statistics  implements Serializable {
    private  Integer  id;//楼栋编号
    private  String  buildName;//楼栋名称
    private  Integer  buildRoomCount;//房间总数
    private  Integer   rented;//房间出租数量
    private  Integer   sold;//房间出售数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getBuildRoomCount() {
        return buildRoomCount;
    }

    public void setBuildRoomCount(Integer buildRoomCount) {
        this.buildRoomCount = buildRoomCount;
    }

    public Integer getRented() {
        return rented;
    }

    public void setRented(Integer rented) {
        this.rented = rented;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", buildName='" + buildName + '\'' +
                ", buildRoomCount=" + buildRoomCount +
                ", rented=" + rented +
                ", sold=" + sold +
                '}';
    }
}

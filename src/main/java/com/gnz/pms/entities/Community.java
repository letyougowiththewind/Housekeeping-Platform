package com.gnz.pms.entities;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
public class Community implements Serializable {
    private Integer id;  //小区在数据库中的主键值
    private String cCompanyName;  //公司名称   ["属性名"："属性值","属性名"："属性值","属性名"："属性值"]
    private Integer communityNum; //小区的编号
    @JSONField(name="name")    //residentialName 替换为name
    private String residentialName;// 小区名字
    private Double coverArea;  //覆盖面积
    private Double constructionArea;  //建筑面积
    private Double greenArea; //绿化面积
    private Double roadArea; //道路面积
    private Integer buildCount; //楼栋的数量
    private String principal;// 负责人姓名
    private String cAddr;// 小区地址
    private Integer developer_id; //开发商编号
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getcCompanyName() {
        return cCompanyName;
    }

    public void setcCompanyName(String cCompanyName) {
        this.cCompanyName = cCompanyName;
    }

    public Integer getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(Integer communityNum) {
        this.communityNum = communityNum;
    }

    public String getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public Double getCoverArea() {
        return coverArea;
    }

    public void setCoverArea(Double coverArea) {
        this.coverArea = coverArea;
    }

    public Double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Double getGreenArea() {
        return greenArea;
    }

    public void setGreenArea(Double greenArea) {
        this.greenArea = greenArea;
    }

    public Double getRoadArea() {
        return roadArea;
    }

    public void setRoadArea(Double roadArea) {
        this.roadArea = roadArea;
    }

    public Integer getBuildCount() {
        return buildCount;
    }

    public void setBuildCount(Integer buildCount) {
        this.buildCount = buildCount;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getcAddr() {
        return cAddr;
    }

    public void setcAddr(String cAddr) {
        this.cAddr = cAddr;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    @Override
    public String toString() {
        return "Community [id=" + id + ", cCompanyName=" + cCompanyName + ", communityNum=" + communityNum
                + ", residentialName=" + residentialName + ", coverArea=" + coverArea + ", constructionArea="
                + constructionArea + ", greenArea=" + greenArea + ", roadArea=" + roadArea + ", buildCount="
                + buildCount + ", principal=" + principal + ", cAddr=" + cAddr + ", developer_id=" + developer_id + "]";
    }
}

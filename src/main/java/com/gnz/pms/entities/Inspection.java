package com.gnz.pms.entities;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Date;
public class Inspection implements Serializable {
    private  Integer id;
    private  String  itemName;
    @JSONField(format="yyyy-MM-dd")
    private Date acceptDate;
    @JSONField(format="yyyy-MM-dd")
    private  Date  confirmDate;
    @JSONField(format="yyyy-MM-dd")
    private  String  accept;//是否合格
    private  String  personnel;//验收人
    private String  ownerOpinion;//业主意见
    private  String personnelOpinion;//房管员意见
    private  String remake;//备注
    private  Integer room_id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Date getAcceptDate() {
        return acceptDate;
    }
    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }
    public Date getConfirmDate() {
        return confirmDate;
    }
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
    public String getAccept() {
        return accept;
    }
    public void setAccept(String accept) {
        this.accept = accept;
    }
    public String getPersonnel() {
        return personnel;
    }
    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }
    public String getOwnerOpinion() {
        return ownerOpinion;
    }
    public void setOwnerOpinion(String ownerOpinion) {
        this.ownerOpinion = ownerOpinion;
    }
    public String getPersonnelOpinion() {
        return personnelOpinion;
    }
    public void setPersonnelOpinion(String personnelOpinion) {
        this.personnelOpinion = personnelOpinion;
    }
    public String getRemake() {
        return remake;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }
    public Integer getRoom_id() {
        return room_id;
    }
    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", acceptDate=" + acceptDate +
                ", confirmDate=" + confirmDate +
                ", accept='" + accept + '\'' +
                ", personnel='" + personnel + '\'' +
                ", ownerOpinion='" + ownerOpinion + '\'' +
                ", personnelOpinion='" + personnelOpinion + '\'' +
                ", remake='" + remake + '\'' +
                ", room_id=" + room_id +
                '}';
    }
}

package com.gnz.pms.dao;
import com.gnz.pms.entities.*;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IResidenceDAO {
    /**
     * 根据小区的编号查询对应的开发商信息
     * @param cid 小区编号
     * @return
     * @throws Exception
     */
    public Developer selectDeveloperById(Integer  cid) throws  Exception;
    /**
     * 添加房间信息
     * @param room
     * @return
     * @throws Exception
     */
    public  int  insertRoom(Room room) throws  Exception;
    /**
     * 添加楼层信息
     * @return
     * @throws Exception
     */
    public   int  insertFloor(Floor floor) throws  Exception;
    /**
     * 添加楼栋信息
     * @param building
     * @return
     * @throws Exception
     */
    public  int  insertBuilding(Building  building) throws  Exception;
    /**
     * 添加小区信息
     * @param community
     * @return
     * @throws Exception
     */
     public  int  insertCommunity(Community  community) throws  Exception;
    /**
     * 根据楼栋编号查询楼栋的数量
     * @param bid
     * @return
     * @throws Exception
     */
     public  Integer  selectRoomCount(Integer  bid) throws  Exception;
    /**
     * 根据楼栋编号查询出 出租的房间数量 或者出售的房间数量
     * @param bid  楼栋编号
     * @param status  出租状态
     * @return
     * @throws Exception
     */
     public  Integer  selectRentedOrSoldNum(int  bid,int status) throws  Exception;
}

package com.gnz.pms.service;
import com.gnz.pms.entities.*;

import java.util.List;
public  interface   IResidenceService {
    /**
     * 根据小区的编号查询对应的开发商信息
     * @param cid 小区编号
     * @return
     * @throws Exception
     */
    public Developer findDeveloperById(Integer  cid) throws  Exception;
    /**
     * 添加房间信息
     * @param room
     * @return
     * @throws Exception
     */
    public  boolean  addRoom(Room room) throws  Exception;
    /**
     * 添加楼层信息
     * @return
     * @throws Exception
     */
    public   boolean  addFloor(Floor floor) throws  Exception;
    /**
     * 添加楼栋信息
     * @param building
     * @return
     * @throws Exception
     */
    public  boolean  addBuilding(Building building) throws  Exception;
    /**
     * 添加小区信息
     * @param community
     * @return
     * @throws Exception
     */
    public  boolean addCommunity(Community community) throws  Exception;
    /**
     * 统计住宅的状态信息
     * @param cid 小区编号
     * @return
     * @throws Exception
     */
    public   List<Statistics>   findStatistics(Integer  cid) throws Exception;
}

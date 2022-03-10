package com.gnz.pms.service;

import com.gnz.pms.entities.*;

import java.util.List;
public interface IContractService {
    /**
     * 查询小区信息
     * @return
     * @throws Exception
     */
     public List<Community>    findCommunity() throws  Exception;
    /**
     * 查询楼栋信息（根据小区编号）
     * @param cid  要查询的小区编号
     * @return
     * @throws Exception
     */
     public  List<Building>  findBuildings(Integer  cid) throws Exception;
    /**
     * 根据楼栋编号查询楼层信息
     * @param bid 要查询的楼栋编号
     * @return
     * @throws Exception
     */
     public  List<Floor>  findFloors(Integer  bid) throws Exception;
    /**
     * 根据楼层编号查询房间信息
     * @param fid  要查询的楼层编号
     * @return
     * @throws Exception
     */
     public  List<Room>  findRooms(Integer  fid) throws Exception;
    /**
     * 添加合同的方法
     * @return
     * @throws Exception
     */
     public  boolean   createContract(Contract contract) throws  Exception;
}

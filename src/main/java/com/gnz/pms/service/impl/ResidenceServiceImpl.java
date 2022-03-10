package com.gnz.pms.service.impl;
import com.gnz.pms.dao.IContractDAO;
import com.gnz.pms.dao.IResidenceDAO;
import com.gnz.pms.entities.*;
import com.gnz.pms.service.IResidenceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ResidenceServiceImpl implements IResidenceService {
    @Resource
    private IResidenceDAO  residenceDAO;
    @Resource
    private IContractDAO  contractDAO;
    @Override
    public Developer findDeveloperById(Integer cid) throws Exception {
        return  residenceDAO.selectDeveloperById(cid);
    }
    @Override
    public boolean addRoom(Room room) throws Exception {
        return  residenceDAO.insertRoom(room)>0;
    }
    @Override
    public boolean addFloor(Floor floor) throws Exception {
        return residenceDAO.insertFloor(floor)>0;
    }
    @Override
    public boolean addBuilding(Building building) throws Exception {
        return residenceDAO.insertBuilding(building)>0;
    }
    @Override
    public boolean addCommunity(Community community) throws Exception {
        return   residenceDAO.insertCommunity(community)>0;
    }
    @Override
    public List<Statistics> findStatistics(Integer cid) throws Exception {
         //创建一个List集合保存统计信息
         List<Statistics>  list=new ArrayList<>();
          //根据小区编号查询出所有的楼栋信息
          List<Building> bds=contractDAO.selectBuildings(cid);
          //遍历楼栋的集合
          Iterator<Building> iter=bds.iterator();
          Statistics  statistics=null;
          Integer  bid=null;
          while(iter.hasNext()){
              statistics=new Statistics();
              Building  b=iter.next();
              bid=b.getId();
              //保存了楼栋信息
              statistics.setBuildName(b.getBuildName());
              //保存了当前楼栋的房间总数
              statistics.setBuildRoomCount(residenceDAO.selectRoomCount(bid));
              //取得房间的出租数量保存
              statistics.setRented(residenceDAO.selectRentedOrSoldNum(bid,1));
              //取得房间的出售数量保存
              statistics.setSold(residenceDAO.selectRentedOrSoldNum(bid,3));
              list.add(statistics);
          }
          return   list;
    }
}

package com.gnz.pms.dao;
import com.gnz.pms.entities.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface IContractDAO  extends IBaseDAO<Integer, Contract>{
    /**
     * 根据小区编号查询所有楼栋
     * @param cid  小区编号
     * @return
     * @throws Exception
     */
    public List<Building>  selectBuildings(Integer  cid) throws  Exception;
    /**
     * 根据楼栋编号查询楼层信息（集合）
     * @param bid 楼栋编号
     * @return
     * @throws Exception
     */
    public  List<Floor>    selectFloors(Integer  bid) throws  Exception;
    /**
     * 根据楼层编查询所有房间信息
     * @param fid  楼层编号
     * @return
     * @throws Exception
     */
    public  List<Room>    selectRooms(Integer  fid) throws  Exception;
    /**
     * 查询所有小区信息
     * @return
     * @throws Exception
     */
    public  List<Community>  selectCommunities() throws  Exception;
    /**
     * 修改房间的出租状态
     * @param rid  房间编号
     * @param status  出租状态
     * @return
     * @throws Exception
     */
    public int   updateRoomStatus(Integer  rid,Integer  status) throws  Exception;

}

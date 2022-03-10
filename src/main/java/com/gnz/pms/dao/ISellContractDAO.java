package com.gnz.pms.dao;
import com.gnz.pms.entities.Properietor;
import com.gnz.pms.entities.Room;
import com.gnz.pms.entities.SellContract;
import net.sf.ehcache.transaction.xa.ExpiredXidTransactionIDImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
@Mapper
public interface ISellContractDAO extends  IBaseDAO<Integer, SellContract>{
    /**
     * 增加业主信息
     * @param properietor  保存了业主信息的对象
     * @return
     * @throws Exception
     */
    public  int   insertProperietor(Properietor  properietor) throws  Exception;
    /**
     * 查询房间信息
     * @param cid  小区编号
     * @param bid  楼栋编号
     * @param fid  楼层编号
     * @param status  出租状态
     * @param cp   当前页
     * @param ls   每页显示的数据量
     * @return
     * @throws Exception
     */
    public List<Room> selectAllRooms(Integer cid,Integer  bid,Integer fid,Integer status,Integer  cp,Integer  ls) throws  Exception;
    /**
     * 统计房间数量信息
     * @param cid 小区编号
     * @param bid  楼栋编号
     * @param fid  楼层编号
     * @param status 出租状态
     * @return
     * @throws Exception
     */
    public  Integer selectCountRooms(Integer cid,Integer  bid,Integer fid,Integer status) throws  Exception;

    /**
     * 统计当前月销售额
     * @return
     * @throws Exception
     */
    public Integer allSellContract(String startDate,String finalDate) throws Exception;

    /**
     * 统计当年订单数量
     * @return
     * @throws Exception
     */
    public Integer allOrder(String start,String end) throws Exception;

    /**
     * 按天统计本年订单数量
     * @return
     * @throws Exception
     */
    public List<Integer> allOrderDay(String start,String end) throws Exception;

    /**
     * 分别统计截至目前和截止上周的订单数量
     * @param date
     * @return
     * @throws Exception
     */
    public Integer allOrderLastWeek(String date) throws Exception;
    public Integer allOrderThisWeek(String date) throws Exception;
}

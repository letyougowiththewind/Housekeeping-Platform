package com.gnz.pms.service;
import com.gnz.pms.entities.SellContract;

import java.util.List;
import java.util.Map;
public interface ISellcontractService {
    /**
     * 增加合同的方法
     * @param sellContract
     * @return
     * @throws Exception
     */
    public  boolean  createSellContract(SellContract sellContract)  throws  Exception;
    /**
     * 查询房间信息
     * @param cid 小区编号
     * @param bid 楼栋编号
     * @param fid 楼层编号
     * @param status 出租状态
     * @param cp
     * @param ls
     * @return
     * @throws Exception
     */
    public Map<String,Object> findAllRooms(Integer cid,Integer bid,Integer fid,Integer status,Integer  cp,Integer ls)  throws  Exception;
    /**
     *根据房间的编号 查询对应的销售合同信息
     * @param rid  房间的编号
     * @return
     * @throws Exception
     */
    public   SellContract   findSellContractByRid(Integer   rid)  throws  Exception;

    /**
     * 计算当月销售额
     * @return
     * @throws Exception
     */
    public Integer allSellContract(String startDate,String finalDate) throws Exception;

    /**
     * 计算当年订单数量
     * @return
     * @throws Exception
     */
    public Integer allOrder(String start,String end) throws Exception;

    /**
     * 按天统计本年订单数量
     * @return
     * @throws Exception
     */
    public List<Integer> allOrderDay(String start, String end) throws Exception;

    public Integer allOrderLastWeek(String date) throws Exception;
    public Integer allOrderThisWeek(String date) throws Exception;

    public  Map<String, Object> returnMap() throws Exception;
}

package com.gnz.pms.service.impl;
import com.gnz.pms.dao.IContractDAO;
import com.gnz.pms.dao.ISellContractDAO;
import com.gnz.pms.entities.Properietor;
import com.gnz.pms.entities.SellContract;
import com.gnz.pms.service.ISellcontractService;
import com.gnz.pms.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class SellContractServiceImpl  implements ISellcontractService {
    @Resource
    private ISellContractDAO  sellContractDAO;
    @Resource
    private IContractDAO  contractDAO;
    @Override
    @Transactional
    public boolean createSellContract(SellContract sellContract) throws Exception {
        boolean  flag=true;
        //增加业主信息（从合同SellContract对象中提取业主信息来创建业主对象，这些信息是从前端表单提交而来）
        Properietor  properietor=new Properietor();
        properietor.setProprietorName(sellContract.getProprietorName());
        properietor.setAddress(sellContract.getAddress());
        properietor.setIDNum(sellContract.getIDNum());
        properietor.setPhone(sellContract.getPhone());
        //将业主的信息插入到数据库
        flag=sellContractDAO.insertProperietor(properietor)>0;
        //定义自己的算法生成合同编号
        sellContract.setSellcontract_id(new SimpleDateFormat("yyyyMMdd").format(sellContract.getSigntime())+ UUID.randomUUID().toString().replace("-",""));
        //将合同信息插入到数据库
        flag=sellContractDAO.insert(sellContract)>0;
        //修改房间的出租状态（修改为3，3表示已经出售）
        flag=contractDAO.updateRoomStatus(sellContract.getRoom_id(),3)>0;
        return flag;
    }
    @Override
    public Map<String, Object> findAllRooms(Integer cid, Integer bid, Integer fid, Integer status, Integer cp, Integer ls) throws Exception {
        Map<String,Object>  map=new HashMap<String, Object>();
        map.put("list",sellContractDAO.selectAllRooms(cid,bid,fid,status,(cp-1)*ls,ls));
        map.put("cp",cp);
        map.put("ls",ls);
        int  count=sellContractDAO.selectCountRooms(cid,bid,fid,status);
        int  allPages=count/ls+ (count%ls==0?0:1);
        map.put("count",count);
        map.put("allPage",allPages);
        map.put("cid",cid);
        map.put("bid",bid);
        map.put("fid",fid);
        map.put("status",status);
        return map;
    }
    @Override
    public SellContract findSellContractByRid(Integer rid) throws Exception {
        return  sellContractDAO.selectById(rid);
    }

    @Override
    public Integer allSellContract(String startDate, String finalDate) throws Exception {
        return sellContractDAO.allSellContract(startDate,finalDate);
    }

    @Override
    public Integer allOrder(String start,String end) throws Exception {
        return sellContractDAO.allOrder(start,end);
    }

    @Override
    public List<Integer> allOrderDay(String start, String end) throws Exception {
        return sellContractDAO.allOrderDay(start,end);
    }

    @Override
    public Integer allOrderLastWeek(String date) throws Exception {
        return sellContractDAO.allOrderLastWeek(date);
    }

    @Override
    public Integer allOrderThisWeek(String date) throws Exception {
        return sellContractDAO.allOrderThisWeek(date);
    }

    @Override
    public Map<String, Object> returnMap() throws Exception {
        //保存最近七天的日期数组
        List<String> days = DateUtils.getDays(7);
        //保存最近14天的日期数组
        List<String> daym = DateUtils.getDays(14);
        //保存最近七天订单数量的数组
        List<Integer> orders = allOrderDay(days.get(0), days.get(6));
        //对最近的两个七天的订单数量进行计算，求出增长/降低幅度
        //截止目前的订单数量
        int allOrder = allOrderThisWeek(days.get(6));
        System.out.println(orders.toString());
        double addThis = allOrderThisWeek(days.get(6))-allOrderLastWeek(days.get(0));
        double addLast = allOrderThisWeek(daym.get(6))-allOrderLastWeek(daym.get(0));
        Map<String, Object> map = new HashMap<>();
        if(addThis>addLast){
            double add = (addThis-addLast)/addLast;
            map.put("increase",add);
        }else if(addThis==addLast){
            map.put("increase",0);
        }else{
            double reduce =(addLast-addThis)/addLast;
            map.put("increase",reduce);
        }
        map.put("days",days);
        map.put("orders",orders);
        //最近一周订单数量
        map.put("weekOrder",addThis);
        //截止今天订单总数
        map.put("allOrder",allOrder);
        //最近一周销售额
        return map;
    }
}

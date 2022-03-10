package com.gnz.pms.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gnz.pms.entities.SellContract;
import com.gnz.pms.entities.Series;
import com.gnz.pms.listener.ShiroSessionListener;
import com.gnz.pms.service.ISellcontractService;
import com.gnz.pms.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

import static com.gnz.pms.utils.DateUtils.assemblyDay;

@Controller
@RequestMapping("sellContract")
public class SellContractAction  extends BaseController {
    @Resource
    private ISellcontractService sellcontractService;

    @Resource
    private ShiroSessionListener shiroSessionListener;

    //添加销售合同的方法
    @RequestMapping("add")
    public  String  addSellContract(SellContract contract) throws  Exception{
         sellcontractService.createSellContract(contract);
         return "res/residence_sell";
    }
    @RequestMapping("list")
    public   String    getAllSoldRooms(Integer cid,Integer bid,Integer fid,Integer status,
                                       @RequestParam(value = "cp",defaultValue = "1") Integer cp,
                                       @RequestParam(value = "ls",defaultValue = "20") Integer ls,Model model)  throws  Exception{
        model.addAttribute("map",sellcontractService.findAllRooms(cid,bid,fid,status,cp,ls));
        return  "sellConstract/sellConstract";
    }
    //根据房间编号查询对应的销售合同
    @RequestMapping("lookSellContract")
    public   String   getSellContract(Integer  rid,Model  model)  throws  Exception{

        model.addAttribute("contract",sellcontractService.findSellContractByRid(rid));

        return  "sellConstract/sellcontract_info";
    }

    /**
     * 实现首页收入模块的信息展示
     * @param startDate
     * @param finalDate
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("income")
    @ResponseBody
    public Map<String,String> allSellContract(String startDate, String finalDate, Model model) throws Exception{
        Map<String, String> map = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        startDate = DateUtils.assemblyDate().get(0);
        finalDate = DateUtils.assemblyDate().get(1);
        map.put("month",String.valueOf(calendar.get(Calendar.MONTH) + 1));
        map.put("startDate",startDate);
        map.put("finalDate",finalDate);
        map.put("income",String.valueOf(sellcontractService.allSellContract(startDate,finalDate)));
        return map;
    }

    /**
     * 实现首页订单模块的信息展示
     * @return
     * @throws Exception
     */
    @RequestMapping("order")
    @ResponseBody
    public Map<String,String> allSellContract(String start,String end) throws Exception{
        Calendar calendar = Calendar.getInstance();
        start = DateUtils.assemblyYear().get(0);
        end = DateUtils.assemblyYear().get(1);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("year",String.valueOf(calendar.get(Calendar.YEAR)));
        map.put("order",String.valueOf(sellcontractService.allOrder(start, end)));
        //会话人数
        map.put("count",String.valueOf(shiroSessionListener.getSessionCount()));
        return map;
    }

    @RequestMapping("select_order_day")
    @ResponseBody
    public Map<String, Object> selectOrdersByDay() throws Exception{
        ModelAndView modelAndView= new ModelAndView();
        Map<String, Object> map = sellcontractService.returnMap();
        modelAndView.addObject("map",map);
        return map;
    }

    @RequestMapping("/echartsView")
    @ResponseBody
    public String echartsView()
    {
        List<String> xAxisData = new ArrayList<String>();
        List< JSONObject> seriesList = new ArrayList< JSONObject>();
        for (int i = 1; i < 32; i++)
        {
            xAxisData.add(i+"月");
        }
        for (int i = 1; i < 4; i++)
        {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 1; j < 13; j++)
            {
                list.add((int)(Math.random()*100));
            }
            Series series = new Series("销售"+i, Series.TYPE_LINE, list);
            JSONObject job = new JSONObject();
            job.put("name", series.toName());
            job.put("type", "bar");
            job.put("data",series.data);
            seriesList.add(job);
        }
        //xAxisData和seriesList转为json
        JSONObject jsob = new JSONObject();
        jsob.put("xAxisData", xAxisData);
        jsob.put("seriesList", seriesList);
        return jsob.toString();
    }

    @Override
    public String getDir() {
        return null;
    }
}
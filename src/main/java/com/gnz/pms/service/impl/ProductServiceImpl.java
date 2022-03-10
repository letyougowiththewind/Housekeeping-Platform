package com.gnz.pms.service.impl;

import com.gnz.pms.dao.IProductDAO;
import com.gnz.pms.entities.Page;
import com.gnz.pms.entities.Product;
import com.gnz.pms.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService {

    @Resource
    private IProductDAO productDAO;

    @Override
    public Product productFindById(String pid) throws Exception {
        return productDAO.productFindById(pid);
    }

    @Override
    public Map<String, Object> productFindAllSplit(Map<String, Object> parammap) throws Exception {
        //创建一个Map集合保存查询到的商品相关的信息
        Map<String, Object> allList = new HashMap<>();
        //开始调用数据层的方法查找数据
        allList.put("allList", productDAO.productFindSplit(parammap));
        //统计数据量
        int number = productDAO.selectCount((String)parammap.get("kw"));
        //每页显示的数据量
        int ls = (int) parammap.get("ls");
        //根据总数据量和每页显示的数据量计算出分页信息，如果这里不加括号的话，
        //会把等号前面的所有内容当成判断条件，从而使得保存在集合中的allpages变成1
        int allPages=number/ls+(number%ls==0?0:1);//注意加括号
        //创建一个Pgae类对象
        Page page=new Page((int)parammap.get("cp"), allPages);
        //把分页信息保存到allList(Map)集合中
        allList.put("page", page);
        allList.put("allPages",allPages);
//		allList.put("number", number);
        return allList;
    }
}

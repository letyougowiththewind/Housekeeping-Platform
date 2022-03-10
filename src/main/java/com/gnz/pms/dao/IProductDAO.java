package com.gnz.pms.dao;

import com.gnz.pms.entities.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface IProductDAO extends IBaseDAO<Integer, Product>{

    public Product productFindById(String pid) throws Exception;

    public List<Product> productFindSplit(Map<String, Object> map) throws Exception;
}

package com.gnz.pms.service;

import com.gnz.pms.entities.Product;

import java.util.Map;

public interface IProductService {

    public Product productFindById(String pid) throws Exception;

    public Map<String,Object> productFindAllSplit(Map<String,Object> paramap) throws Exception;
}

package com.gnz.pms.dao;
import com.gnz.pms.entities.Tenant;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ITenantDAO   extends   IBaseDAO<Integer, Tenant>{
}

package com.gnz.pms.dao;
import com.gnz.pms.entities.Permission;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IPermissionDAO  extends  IBaseDAO<Integer, Permission> {
}

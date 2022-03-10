package com.gnz.pms.dao;

import com.gnz.pms.entities.FileInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPhotoDAO extends IBaseDAO<Integer, FileInformation>{
}

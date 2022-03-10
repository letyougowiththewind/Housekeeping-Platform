package com.gnz.pms.service;

import com.gnz.pms.entities.Inspection;

import java.util.Map;

public interface IHomeInspectionService {
    /**
     * 增加验房记录
     * @param item
     * @return
     * @throws Exception
     */
   public  boolean  createInspectionItem(Inspection  item) throws Exception;
    /**
     *  根据房间编号查询该房间的验房记录
     * @param rid  房间编号
     * @return
     * @throws Exception
     */
   public Map<String,Object>  findInspectionItemsByRid(Integer  rid) throws Exception;
    /**
     * 删除 验房记录
     * @param id  该id并不是房间的编号 而是验房记录的编号
     * @return
     * @throws Exception
     */
   public boolean  removeInspectionItemById(Integer  id) throws  Exception;
}

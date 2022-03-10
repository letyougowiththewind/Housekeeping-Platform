package com.gnz.pms.dao;
import com.gnz.pms.entities.Inspection;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface IHomeInspectionDAO  extends IBaseDAO<Integer,Inspection> {
   //插入的方法父接口已经有了
    /**
     * 查询验房记录
     * @param rid
     * @return
     * @throws Exception
     */
    public List<Inspection>   selectInspectionItemByRid(Integer rid)  throws  Exception;
}

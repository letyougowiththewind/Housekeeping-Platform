package com.gnz.pms.service;
import com.gnz.pms.entities.Tenant;
import java.util.Map;
public interface ITenantService {
    /**
     * 添加租户信息
     * @param vo
     * @return
     * @throws Exception
     */
     public  boolean   createTenant(Tenant vo) throws  Exception;
    /**
     * 修改租户信息
     * @param vo
     * @return
     * @throws Exception
     */
     public  boolean   editTenant(Tenant vo) throws  Exception;
    /**
     *  实现模糊分页查询
     * @param kw 模糊查询的关键字
     * @param cp  当前页
     * @param ls  每页显示的数据量
     * @return
     * @throws Exception
     */
     public Map<String,Object>  findAllSplit(String kw,Integer cp,Integer  ls) throws  Exception;
    /**
     * 根据编号删除租户信息
     * @param id  租户的编号
     * @return
     * @throws Exception
     */
     public boolean   removeById(Integer  id) throws  Exception;
}

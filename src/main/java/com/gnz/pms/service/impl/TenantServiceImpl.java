package com.gnz.pms.service.impl;
import com.gnz.pms.dao.ITenantDAO;
import com.gnz.pms.entities.Tenant;
import com.gnz.pms.service.ITenantService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Service
public class TenantServiceImpl  implements ITenantService {
    @Resource
    private ITenantDAO  tenantDAO;
    @Override
    public boolean createTenant(Tenant vo) throws Exception {
        return tenantDAO.insert(vo)>0;
    }
    @Override
    public boolean editTenant(Tenant vo) throws Exception {
        return  tenantDAO.update(vo)>0;
    }
    @Override
    public Map<String, Object> findAllSplit(String kw, Integer cp, Integer ls) throws Exception {
        Map<String,Object>  map=new HashMap<String, Object>();
        //将查询到的用户集合保存到map集合中
        map.put("list",tenantDAO.selectSplitAll("%"+kw+"%",(cp-1)*ls,ls));
        //统计数据量
        int  count =tenantDAO.selectCount("%"+kw+"%");
        //计算出总页数并且保存到map集合中
        map.put("allPages",count/ls+(count%ls==0?0:1));
        //将当前页、每页显示的数据量、模糊查询的关键字、总的数据量保存到map集合中
        map.put("cp",cp);
        map.put("ls",ls);
        map.put("kw",kw);
        map.put("count",count);
        return map;  //返回的是map集合
    }
    @Override
    public boolean removeById(Integer id) throws Exception {
        return  tenantDAO.deleteById(id)>0;
    }
}

package com.gnz.pms.service.impl;
import com.gnz.pms.dao.IHomeInspectionDAO;
import com.gnz.pms.entities.Inspection;
import com.gnz.pms.service.IHomeInspectionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Service
public class InspectionServiceImpl  implements IHomeInspectionService {
    @Resource
    private IHomeInspectionDAO inspectionDAO;
    @Override
    public boolean createInspectionItem(Inspection item) throws Exception {
        return  inspectionDAO.insert(item)>0;
    }
    @Override
    public Map<String, Object> findInspectionItemsByRid(Integer rid) throws Exception {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("list",inspectionDAO.selectInspectionItemByRid(rid));
        return  map;
    }
    @Override
    public boolean removeInspectionItemById(Integer id) throws Exception {
        return inspectionDAO.deleteById(id)>0;
    }
}

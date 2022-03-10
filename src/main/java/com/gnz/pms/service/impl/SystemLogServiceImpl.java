package com.gnz.pms.service.impl;


import com.gnz.pms.dao.SystemLogMapper;
import com.gnz.pms.entities.SystemLog;
import com.gnz.pms.service.ISystemLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class SystemLogServiceImpl implements ISystemLogService {

    @Resource
    private SystemLogMapper logMapper;

    @Override
    public int addLog(SystemLog log) {
        return logMapper.addLog(log);
    }

    @Override
    public Map<String, Object> findAllSplitLogs(String kw, Integer cp, Integer ls) throws Exception {
        Map<String,Object> map=new HashMap<>();
        //将查询到的用户集合保存到map集合中
        map.put("list",logMapper.selectSplitAllLogs("%"+kw+"%",(cp-1)*ls,ls));
        //统计数据量
        int  count =logMapper.selectCountLogs("%"+kw+"%");
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
    public boolean removeLogById(Integer uid) throws Exception {
        return   logMapper.deleteLogById(uid)>0;
    }
}

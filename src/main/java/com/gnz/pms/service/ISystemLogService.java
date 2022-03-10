package com.gnz.pms.service;


import com.gnz.pms.entities.SystemLog;

import java.util.Map;


public interface ISystemLogService {

    int addLog(SystemLog log);

    /**
     * 实现模糊分页查询，调用dao层的如下两个方法：
     * selectSplitAllLogs()，取得用户列表集合
     * selectCount()，取得查询到的数据量
     * @param kw 模糊查询的关键字
     * @param cp 当前页
     * @param ls 每页显示的数量
     * @return  返回保存了查询到的数据的map集合
     * @throws Exception
     */
    public Map<String,Object> findAllSplitLogs(String kw, Integer cp, Integer ls) throws Exception;

    /**
     * 根据编号删除用户信息
     * @param uid 要删除的用户编号
     * @return
     * @throws Exception
     */
    public boolean removeLogById(Integer uid) throws Exception;
}

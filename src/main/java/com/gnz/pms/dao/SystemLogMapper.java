package com.gnz.pms.dao;

import com.gnz.pms.entities.SystemLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SystemLogMapper<K,V> {
    int addLog(SystemLog log);
    /**
     * 实现模糊分页查询
     * @param kw  模糊查询的关键字
     * @param cp  当前页
     * @param ls  每页显示的数据量
     * @return
     * @throws Exception
     */
    public List<V> selectSplitAllLogs(String  kw, Integer cp, Integer ls) throws Exception;
    /**
     * 统计数据量
     * @param kw 模糊查询的关键字
     * @return   返回查询到的数据量
     * @throws Exception
     */
    public  int    selectCountLogs(String kw) throws Exception;

    /**
     * 根据编号删除日志
     * @param id
     * @return
     * @throws Exception
     */
    public  int  deleteLogById(K id) throws Exception;
}

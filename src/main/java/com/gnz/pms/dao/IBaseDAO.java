package com.gnz.pms.dao;
import java.util.List;
public interface IBaseDAO<K,V>{
    /**
     * 增加数据
     * @param vo  保存了要插入数据的对象
     * @return  增加的行数
     * @throws Exception
     */
    public    int   insert(V  vo) throws Exception;
    /**
     * 修改数据
     * @param vo  保存了要修改的数据的对象
     * @return 修改的行数
     * @throws Exception
     */
    public    int  update(V  vo) throws Exception;
    /**
     * 根据编号删除数据
     * @param id 要删除的数据的编号
     * @return 删除的行数
     * @throws Exception
     */
    public  int  deleteById(K id) throws Exception;
    /**
     * 实现批量删除
     * @param ids  保存了要删除的数据的编号的集合
     * @return  删除的函数
     * @throws Exception
     */
    public  int  deleteBatch(List<K> ids) throws Exception;
    /**
     * 根据编号查询数据
     * @param id  要查询的数据的编号
     * @return  如果有数据对象  否则返回 null
     * @throws Exception
     */
    public   V  selectById(K  id) throws Exception;
    /**
     * 实现模糊分页查询
     * @param kw  模糊查询的关键字
     * @param cp  当前页
     * @param ls  每页现实的数据量
     * @return
     * @throws Exception
     */
    public List<V> selectSplitAll(String  kw, Integer cp, Integer ls) throws Exception;
    /**
     * 统计数据量
     * @param kw 模糊查询的关键字
     * @return   返回查询到的数据量
     * @throws Exception
     */
    public  int    selectCount(String kw) throws Exception;
}
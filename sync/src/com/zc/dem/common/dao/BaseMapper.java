package com.zc.dem.common.dao;

import java.util.List;

/**
 * Mybatis Mapper基础类，Dao接口继承此接口
 * 此接口封装了一些单表操作的公共方法
 * @author Joehart
 * @version 1.0
 */
public interface BaseMapper<T extends AbstractDO, PK extends java.io.Serializable>
{
    /**
     * 删除记录
     * <br>
     * @param modelPK 主键
     * @author  OuYangGuoDong
     * @see [类、类#方法、类#成员]
     */
    void deleteAll();
    
    /**
     * 查询所有
     * <br> 
     * @param condition
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<T> queryAll();
    
    /**
     *  同步数据
     * <br>
     * <功能详细描述>
     * @param model
     */
    void synData(T model);
}

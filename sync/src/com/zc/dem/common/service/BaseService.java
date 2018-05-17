package com.zc.dem.common.service;

import java.util.List;

import com.zc.dem.common.dao.AbstractDO;

/**
 * 基础Service接口
 * @author Joehart
 * @version 1.0
 */
public interface BaseService<T extends AbstractDO, PK extends java.io.Serializable>
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
     * 修改数据
     * <br>
     * <功能详细描述>
     * @param model
     */
    void synData(List<T> models);
}

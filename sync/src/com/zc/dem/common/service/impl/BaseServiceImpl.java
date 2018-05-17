package com.zc.dem.common.service.impl;

import java.util.List;

import com.zc.dem.common.dao.AbstractDO;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.BaseService;

/**
 * 基础Service实现类
 * @author Joehart
 * @version 1.0
 */
public abstract class BaseServiceImpl<T extends AbstractDO, PK extends java.io.Serializable> implements
    BaseService<T, PK>
{
    /**
     * 获取DAO
     * <br> 
     * @return
     * @author  
     * @see [类、类#方法、类#成员]
     */
    public abstract BaseMapper<T, PK> getDao();
    
  
    /**
     * 删除记录
     * <br>
     * @param modelPK 主键
     * @author  OuYangGuoDong
     * @see [类、类#方法、类#成员]
     */
    public void deleteAll()
    {
        getDao().deleteAll();
    }
    
    /**
     * 查询所有
     * <br> 
     * @param condition
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<T> queryAll()
    {
        return getDao().queryAll();
    }

    @Override
    public void synData(List<T> models)
    {
        for(T t:models)
        {
            getDao().synData(t); 
        }
    }
}

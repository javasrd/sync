package com.zc.dem.syndata.inpatientarea.service;

import java.util.List;

import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;

/**
 * 病区
 * @author Joehart
 * @version 1.0
 */
public interface InpatientAreaService
{
    /***
     * 查询病人表数据
     * 
     * @return 数据
     * @exception Exception e
     * @see [类、类#方法、类#成员]
     */
    List<InpatientAreaBean> getInpatientAreaList();
    
    
    /**
     * 同步结束后删除所有数据
     * 
     * @see [类、类#方法、类#成员]
     */
    void delAllSynInpatientAreas();
    
    /**
     * 数据同步
     * <一句话功能简述>
     * <功能详细描述>
     * @param bean
     * @see [类、类#方法、类#成员]
     */
    void synData(List<InpatientAreaBean> list);
    
    
    /**
     * 查询所有病区
     * <br> 
     * @param condition
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InpatientAreaBean> queryInPatientAreaList();
    
}

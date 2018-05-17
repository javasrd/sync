package com.zc.dem.syndata.drugway.service;

import java.util.List;

import com.zc.dem.syndata.drugway.bean.DrugWayBean;

/**
 *  用药途径
 * @author Joehart
 * @version 1.0
 */
public interface DrugWayService
{
    /***
     * 查询表数据
     * 
     * @return 数据
     * @exception Exception e
     * @see [类、类#方法、类#成员]
     */
    List<DrugWayBean> getDrugWayList();
    
    /**
     * 同步结束后删除所有数据
     * 
     * @see [类、类#方法、类#成员]
     */
    void delAllSynDrugWays();
    
    /**
     * 同步数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param drugWay
     * @see [类、类#方法、类#成员]
     */
    void synData(List<DrugWayBean> drugWayList);
}

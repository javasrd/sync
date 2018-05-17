package com.zc.synhis.service;

import java.util.List;

import com.zc.dem.syndata.drugway.bean.DrugWayBean;

/**
 * 用药途径表PIVAS_DRUGWAY对应DAO
 * @author Joehart
 * @version 1.0
 */
public interface DrugWayByHisService
{
    /**
     * 同步HIS用药途径通过视图
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
   List<DrugWayBean> qryDrugWayList();
}

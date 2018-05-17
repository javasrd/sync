package com.zc.synhis.service;

import java.util.List;

import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;

/**
 * 病区数据DAO
 * @author Joehart
 * @version 1.0
 */
public interface InpatientAreaByHisService
{
    /**
     * 通过调用his病区视图，获取数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InpatientAreaBean> qryInpatientAreaList();
}

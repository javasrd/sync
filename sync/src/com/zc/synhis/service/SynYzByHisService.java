package com.zc.synhis.service;

import java.util.List;
import java.util.Map;

import com.zc.dem.syndata.yz.bean.SynYzBean;

/**
 * @author Joehart
 * @version 1.0
 */
public interface SynYzByHisService
{
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SynYzBean> qryYzList(Map<String, Object> map);
}

package com.zc.dem.syndata.yz.service;

import java.util.List;
import java.util.Map;

import com.zc.dem.common.service.BaseService;
import com.zc.dem.syndata.yz.bean.SynYzBean;

/**
 * 医嘱
 *
 * @author Joehart
 * @version 1.0
 */
public interface SynYzService extends BaseService<SynYzBean, Long>
{
    List<SynYzBean> queryYZForHisDel();
    
    void updateYzByCondition(SynYzBean bean);
    
    void checkYzStatus();
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SynYzBean> qryYzList(Map<String, Object> map);
}

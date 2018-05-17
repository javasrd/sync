package com.zc.dem.syndata.excuteRecord.service;

import java.util.List;
import java.util.Map;

import com.zc.dem.common.service.BaseService;
import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;

/**
 *  医嘱
 * @author Joehart
 * @version 1.0
 */
public interface SynYdRecordService extends BaseService<ExcuteRecordBean, Long>
{
    /**
     * 同步药单执行记录到
     * <一句话功能简述>
     * <功能详细描述>
     * @param bean
     * @see [类、类#方法、类#成员]
     */
    void synDataPivas(ExcuteRecordBean bean);
    
    /**
     * 根据用药时间匹配用用药批次
     *
     */
    String getBatchID(Map<String, Object> map);
    
    /**
     * 
     * 根据药单号获取序列号
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    String getSchedule(Map<String, Object> map);
    
    Integer getYdRecordCount(Map<String, Object> map);
    
    /**
     * 获取pivas预制的批次
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<String> getBathListOther(ExcuteRecordBean bean);
    
    /**
     * 检查批次号是否已被使用
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    String checkBatchUsed(ExcuteRecordBean bean);
    
    /**
     * 获取退费药单
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ExcuteRecordBean> qryYdStateList(Map<String, Object> map);
}

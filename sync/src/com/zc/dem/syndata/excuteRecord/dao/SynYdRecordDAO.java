package com.zc.dem.syndata.excuteRecord.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;

/**
 *  药单执行记录
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("synYdRecordDAO")
public interface SynYdRecordDAO extends BaseMapper<ExcuteRecordBean, Long>
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
    String getBatchID(@Param("qry")Map<String, Object> map);
    
    /**
     * 
     * 根据药单号获取序列号
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    String getSchedule(@Param("qry")Map<String, Object> map);
    
    Integer getYdRecordCount(@Param("qry")Map<String, Object> map);
    
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
    List<ExcuteRecordBean> qryYdStateList(@Param("map") Map<String, Object> map);
}

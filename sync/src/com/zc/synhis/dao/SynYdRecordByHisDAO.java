package com.zc.synhis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;

/**
 * 药单执行记录
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("synYdRecordByHisDAO")
public interface SynYdRecordByHisDAO
{
    /**
     * 从his提供的视图中获取药单执行记录
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ExcuteRecordBean> qryYdRecordList(@Param("map") Map<String, Object> map);
    
    /**
     * 获取退费药单
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ExcuteRecordBean> qryYdStateList(@Param("map") Map<String, Object> map);
}

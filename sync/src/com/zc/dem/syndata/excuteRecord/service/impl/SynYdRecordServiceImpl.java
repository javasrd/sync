package com.zc.dem.syndata.excuteRecord.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.impl.BaseServiceImpl;
import com.zc.dem.common.util.DataBaseUtil;
import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;
import com.zc.dem.syndata.excuteRecord.dao.SynYdRecordDAO;
import com.zc.dem.syndata.excuteRecord.service.SynYdRecordService;

/**
 *  药单执行记录
 * @author Joehart
 * @version 1.0
 */
@Service("synYdRecordService")
public class SynYdRecordServiceImpl extends BaseServiceImpl<ExcuteRecordBean, Long> implements SynYdRecordService
{
    
    @Resource
    private SynYdRecordDAO synYdRecordDAO;
    
    @Resource
    private DataBaseUtil dataBaseUtil;
    
    @Override
    public BaseMapper<ExcuteRecordBean, Long> getDao()
    {
        return synYdRecordDAO;
    }

    @Override
    public void synDataPivas(ExcuteRecordBean bean)
    {
        synYdRecordDAO.synDataPivas(bean);
    }

    @Override
    public String getBatchID(Map<String, Object> map)
    {
        return synYdRecordDAO.getBatchID(map);
    }

    @Override
    public String getSchedule(Map<String, Object> map)
    {
        return synYdRecordDAO.getSchedule(map);
    }

    @Override
    public Integer getYdRecordCount(Map<String, Object> map)
    {
        return synYdRecordDAO.getYdRecordCount(map);
    }

    @Override
    public List<String> getBathListOther(ExcuteRecordBean bean)
    {
        return synYdRecordDAO.getBathListOther(bean);
    }

    @Override
    public String checkBatchUsed(ExcuteRecordBean bean)
    {
        return synYdRecordDAO.checkBatchUsed(bean);
    }

    @Override
    public List<ExcuteRecordBean> qryYdStateList(Map<String, Object> map)
    {
        return synYdRecordDAO.qryYdStateList(map);
    }
    
}

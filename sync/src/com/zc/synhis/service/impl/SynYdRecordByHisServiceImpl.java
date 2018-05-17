package com.zc.synhis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;
import com.zc.synhis.dao.SynYdRecordByHisDAO;
import com.zc.synhis.service.SynYdRecordByHisService;

/**
 * 药单执行记录
 * @author Joehart
 * @version 1.0
 */
@Service("synYdRecordByHisService")
public class SynYdRecordByHisServiceImpl implements SynYdRecordByHisService
{
    @Resource
    private SynYdRecordByHisDAO synYdRecordByHisDAO;
    
    @Override
    public List<ExcuteRecordBean> qryYdRecordList(Map<String, Object> map)
    {
        return synYdRecordByHisDAO.qryYdRecordList(map);
    }

    @Override
    public List<ExcuteRecordBean> qryYdStateList(@Param("map") Map<String, Object> map)
    {
        return synYdRecordByHisDAO.qryYdStateList(map);
    }
    
}

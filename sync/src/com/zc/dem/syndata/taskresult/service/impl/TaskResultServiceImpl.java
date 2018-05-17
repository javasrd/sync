package com.zc.dem.syndata.taskresult.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.impl.BaseServiceImpl;
import com.zc.dem.syndata.taskresult.bean.TaskResultBean;
import com.zc.dem.syndata.taskresult.dao.TaskResultDAO;
import com.zc.dem.syndata.taskresult.service.TaskResultService;

/**
 * 同步设置数据
 * @author Joehart
 * @version 1.0
 */
@Service("taskResultService")
public class TaskResultServiceImpl extends BaseServiceImpl<TaskResultBean, Long> implements TaskResultService
{
    @Resource
    private TaskResultDAO taskResultDAO;
    
    @Override
    public BaseMapper<TaskResultBean, Long> getDao()
    {
        return taskResultDAO;
    }
}

package com.zc.dem.syndata.taskresult.dao;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.taskresult.bean.TaskResultBean;

/**
 * 同步设置数据
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("taskResultDAO")
public interface TaskResultDAO extends BaseMapper<TaskResultBean, Long>
{
    
}

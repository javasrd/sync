package com.zc.dem.schedulejob.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.schedulejob.entity.ScheduleJob;
import com.zc.dem.schedulejob.entity.ScheduleResult;

@MyBatisRepository("scheduleJobDao")
public interface ScheduleJobDao
{
    /**
     * 查询数据块所有任务信息
     * @param 
     * @return List<Schedulejob>
     * @see [类、类#方法、类#成员]
     */
    ArrayList<ScheduleJob> getAllTask();
    
    /**
     * 插入一条任务信息
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertOneTask(ScheduleJob schedulejob);
    
    /**
     * 删除一条任务信息
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    int deleteOneTask(ScheduleJob schedulejob);
    
    /**
     * 查询一条任务信息
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    ScheduleJob selectOneTask(@Param("schedulejob") ScheduleJob schedulejob);
    
    /**
     * 更新一条任务信息
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateOneTask(@Param("schedulejob") ScheduleJob schedulejob);
    
    /**
     * 插入一条任务执行结果信息
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */ 
    int insertRecord(@Param("scheduleResult") ScheduleResult scheduleResult);
}

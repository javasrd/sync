package com.zc.dem.schedulejob.service;

import java.util.Map;

import org.quartz.SchedulerException;

import com.zc.dem.schedulejob.entity.ScheduleJob;

/**
 * @author Joehart
 * @version 1.0
 */
public interface TaskManagerService
{
    /**
     * 添加一个新任务
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map addJob(ScheduleJob schedulejob);
    
    /**
     * 立即启动一个任务
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map startJob(ScheduleJob schedulejob);
    
    /**
     * 立即启动一个任务
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void createJob(ScheduleJob schedulejob)
        throws SchedulerException;
    
    /**
     * 删除一个任务
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map deleteJob(ScheduleJob schedulejob);
    
    /**
     * 更新一个任务执行时间
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map modifyJob(ScheduleJob schedulejob);
    
    /**
     * 查找一个任务是否在运行
     * @param ScheduleJob
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isRunning(ScheduleJob schedulejob);
}

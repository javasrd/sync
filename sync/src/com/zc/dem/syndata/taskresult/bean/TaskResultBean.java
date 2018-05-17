package com.zc.dem.syndata.taskresult.bean;

import java.sql.Timestamp;

import com.zc.dem.common.dao.AbstractDO;

/**
 * 同步设置数据
 * @author Joehart
 * @version 1.0
 */
public class TaskResultBean extends AbstractDO
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 任务唯一标识
     */
    private String taskID;
    
    /**
     * 任务名称
     */
    private String taskName;
    
    /**
     * 任务类型，0,定时任务 1,一次性任务 
     */
    private int taskType;
    
    /**
     * 任务类型名称
     */
    private String taskTypeName;
    
    /**
     * 任务执行结果  0成功  1失败 
     */
    private int taskResult;
    
    /**
     * 执行结果
     */
    private String taskResultName;
    
    /**
     * 任务开始执行时间  YYYY-MM-DD HH:MM:SS
     */
    private Timestamp taskExecStartTime;
    
    /**
     * 任务结束执行时间  YYYY-MM-DD HH:MM:SS
     */
    private Timestamp taskExecStopTime;
    
    /**
     * 任务执行内容类型  0,病人、1,医嘱、2,病区、3,用药方式、4,医嘱频次、5,药品
     */
    private int taskContentType;
    
    /**
     * 任务执行内容类型  0,病人、1,医嘱、2,病区、3,用药方式、4,医嘱频次、5,药品
     */
    private String taskContentTypeName;
    
    /**
     * 预留字段0
     */
    private String reserve0;
    
    /**
     * 预留字段1
     */
    private String reserve1;
    
    /**
     * 预留字段2
     */
    private String reserve2;
    
    public String getTaskID()
    {
        return taskID;
    }
    
    public void setTaskID(String taskID)
    {
        this.taskID = taskID;
    }
    
    public String getTaskName()
    {
        return taskName;
    }
    
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
    
    public int getTaskType()
    {
        return taskType;
    }
    
    public void setTaskType(int taskType)
    {
        this.taskType = taskType;
    }
    
    public String getTaskTypeName()
    {
        return taskTypeName;
    }
    
    public void setTaskTypeName(String taskTypeName)
    {
        this.taskTypeName = taskTypeName;
    }
    
    public int getTaskResult()
    {
        return taskResult;
    }
    
    public void setTaskResult(int taskResult)
    {
        this.taskResult = taskResult;
    }
    
    public String getTaskResultName()
    {
        return taskResultName;
    }
    
    public void setTaskResultName(String taskResultName)
    {
        this.taskResultName = taskResultName;
    }
    
    public Timestamp getTaskExecStartTime()
    {
        return taskExecStartTime;
    }
    
    public void setTaskExecStartTime(Timestamp taskExecStartTime)
    {
        this.taskExecStartTime = taskExecStartTime;
    }
    
    public Timestamp getTaskExecStopTime()
    {
        return taskExecStopTime;
    }
    
    public void setTaskExecStopTime(Timestamp taskExecStopTime)
    {
        this.taskExecStopTime = taskExecStopTime;
    }
    
    public int getTaskContentType()
    {
        return taskContentType;
    }
    
    public void setTaskContentType(int taskContentType)
    {
        this.taskContentType = taskContentType;
    }
    
    public String getTaskContentTypeName()
    {
        return taskContentTypeName;
    }
    
    public void setTaskContentTypeName(String taskContentTypeName)
    {
        this.taskContentTypeName = taskContentTypeName;
    }
    
    public String getReserve0()
    {
        return reserve0;
    }
    
    public void setReserve0(String reserve0)
    {
        this.reserve0 = reserve0;
    }
    
    public String getReserve1()
    {
        return reserve1;
    }
    
    public void setReserve1(String reserve1)
    {
        this.reserve1 = reserve1;
    }
    
    public String getReserve2()
    {
        return reserve2;
    }
    
    public void setReserve2(String reserve2)
    {
        this.reserve2 = reserve2;
    }
    
    @Override
    public String toString()
    {
        return "TaskResultBean [reserve0=" + reserve0 + ", reserve1=" + reserve1 + ", reserve2=" + reserve2
            + ", taskContentType=" + taskContentType + ", taskContentTypeName=" + taskContentTypeName
            + ", taskExecStartTime=" + taskExecStartTime + ", taskExecStopTime=" + taskExecStopTime + ", taskID="
            + taskID + ", taskName=" + taskName + ", taskResult=" + taskResult + ", taskResultName=" + taskResultName
            + ", taskType=" + taskType + ", taskTypeName=" + taskTypeName + "]";
    }
    
}

package com.zc.dem.schedulejob.entity;

import java.util.Date;

public class ScheduleResult
{
    private Long taskID;
    
    private String taskName;
    
    private Integer taskType;
    
    private Integer taskResult;
    
    private Date taskExecStartTime;
    
    private Date taskExecStopTime;
    
    private Integer taskContent;

    public Long getTaskID()
    {
        return taskID;
    }
    
    public void setTaskID(Long taskID)
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
    
    public Integer getTaskType()
    {
        return taskType;
    }
    
    public void setTaskType(Integer taskType)
    {
        this.taskType = taskType;
    }
    
    public Integer getTaskResult()
    {
        return taskResult;
    }
    
    public void setTaskResult(Integer taskResult)
    {
        this.taskResult = taskResult;
    }
    
    public Date getTaskExecStartTime()
    {
        return taskExecStartTime;
    }
    
    public void setTaskExecStartTime(Date taskExecStartTime)
    {
        this.taskExecStartTime = taskExecStartTime;
    }
    
    public Date getTaskExecStopTime()
    {
        return taskExecStopTime;
    }
    
    public void setTaskExecStopTime(Date taskExecStopTime)
    {
        this.taskExecStopTime = taskExecStopTime;
    }

    public Integer getTaskContent()
    {
        return taskContent;
    }
    
    public void setTaskContent(Integer taskContent)
    {
        this.taskContent = taskContent;
    }
}

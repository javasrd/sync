package com.zc.dem.schedulejob.entity;

import java.util.Date;

public class ScheduleJob
{
    public static final String STATUS_RUNNING = "1";
    
    public static final String STATUS_NOT_RUNNING = "0";
    
    public static final Integer TASK_ENABLE = 0;
    
    public static final Integer TASK_DISENABLE = 1;
    
    private Long jobId;
    
    private String jobName;
    
    private Integer jobType;

    private Integer execMode;
    
    private Integer taskPriority;
    
    private Integer runInterval;
    
    private Integer retryTime;
    
    private Integer retryInterval;
    
    private Date startTime;

    private Date createTime;
    
    private Date updateTime;
    
    private Integer action;
    
    private Integer actionBefore;
    
    private Integer jobEnable;

    private String remark;

    private String beanClass;

    public Long getJobId()
    {
        return jobId;
    }
    
    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }
    
    public String getJobName()
    {
        return jobName;
    }
    
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    
    public Integer getRunInterval()
    {
        return runInterval;
    }
    
    public void setRunInterval(Integer runInterval)
    {
        this.runInterval = runInterval;
    }
    
    public Integer getRetryTime()
    {
        return retryTime;
    }
    
    public void setRetryTime(Integer retryTime)
    {
        this.retryTime = retryTime;
    }
    
    public Integer getRetryInterval()
    {
        return retryInterval;
    }
    
    public void setRetryInterval(Integer retryInterval)
    {
        this.retryInterval = retryInterval;
    }
    
    public Integer getTaskPriority()
    {
        return taskPriority;
    }
    
    public void setTaskPriority(Integer taskPriority)
    {
        this.taskPriority = taskPriority;
    }
    
    public Integer getAction()
    {
        return action;
    }
    
    public void setAction(Integer action)
    {
        this.action = action;
    }
    
    public String getBeanClass()
    {
        return beanClass;
    }
    
    public void setBeanClass(String beanClass)
    {
        this.beanClass = beanClass;
    }
    
    public Integer getJobEnable()
    {
        return jobEnable;
    }
    
    public void setJobEnable(Integer jobEnable)
    {
        this.jobEnable = jobEnable;
    }
    
    public Integer getJobType()
    {
        return jobType;
    }
    
    public void setJobType(Integer jobType)
    {
        this.jobType = jobType;
    }
    
    public Integer getExecMode()
    {
        return execMode;
    }
    
    public void setExecMode(Integer execMode)
    {
        this.execMode = execMode;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getActionBefore()
    {
        return actionBefore;
    }

    public void setActionBefore(Integer actionBefore)
    {
        this.actionBefore = actionBefore;
    }
}

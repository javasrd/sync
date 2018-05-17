package com.zc.dem.syndata.medicalfrequency.entity;

import com.zc.dem.common.dao.AbstractDO;

/**
 * 用药频次
 * @author Joehart
 * @version 1.0
 */
public class MedicalFrequency extends AbstractDO
{
    private static final long serialVersionUID = 1L;
    
    /**主键*/
    private Long id;
    
    /**频次编码*/
    private String code;
    
    /**频次名称*/
    private String name;
    
    /**间隔*/
    private Integer interval;
    
    /**一天几次*/
    private Integer timeOfDay;
    
    /**开始时间*/
    private String startTime;
    
    /**结束时间*/
    private String endTime;
    
    /**
     * 0(新增)、1(变更)
     */
    private int action;
    
    private String reserve1;
    
    private String reserve2;
    
    private String reserve3;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code == null ? null : code.trim();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public Integer getInterval()
    {
        return interval;
    }
    
    public void setInterval(Integer interval)
    {
        this.interval = interval;
    }
    
    public Integer getTimeOfDay()
    {
        return timeOfDay;
    }
    
    public void setTimeOfDay(Integer timeOfDay)
    {
        this.timeOfDay = timeOfDay;
    }
    
    public String getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    
    public String getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    
    public String getReserve1()
    {
        return reserve1;
    }
    
    public void setReserve1(String reserve1)
    {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }
    
    public String getReserve2()
    {
        return reserve2;
    }
    
    public void setReserve2(String reserve2)
    {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }
    
    public String getReserve3()
    {
        return reserve3;
    }
    
    public void setReserve3(String reserve3)
    {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }
    
    public int getAction()
    {
        return action;
    }
    
    public void setAction(int action)
    {
        this.action = action;
    }
    
    @Override
    public String toString()
    {
        return "MedicalFrequency [action=" + action + ", code=" + code + ", endTime=" + endTime + ", id=" + id
            + ", interval=" + interval + ", name=" + name + ", reserve1=" + reserve1 + ", reserve2=" + reserve2
            + ", reserve3=" + reserve3 + ", startTime=" + startTime + ", timeOfDay=" + timeOfDay + "]";
    }
    
}
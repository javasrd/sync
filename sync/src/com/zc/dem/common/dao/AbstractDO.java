package com.zc.dem.common.dao;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基础 Data Object 类
 * <br>
 * 基础数据库实体类，数据库实体可以继承此此类
 * @author Joehart
 * @version 1.0
 */
public abstract class AbstractDO implements Serializable
{
    
    private static final long serialVersionUID = -3942149913171834745L;
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
    //public abstract Serializable getId();
}

package com.zc.dem.ActiveMQ;

import java.io.Serializable;

/**
 * 需要发送的信息打包
 *
 * @author Joehart
 * @version 1.0
 */
public class SyncData implements Serializable
{
    private String data;
    
    public String getData()
    {
        return data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
}

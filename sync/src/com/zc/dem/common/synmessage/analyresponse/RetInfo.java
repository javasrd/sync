package com.zc.dem.common.synmessage.analyresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetInfo
{
    @XmlElement(name = "RetCode")
    private String retCode;
    
    @XmlElement(name = "RetCon")
    private String retCon;

    public String getRetCode()
    {
        return retCode;
    }

    public void setRetCode(String retCode)
    {
        this.retCode = retCode;
    }

    public String getRetCon()
    {
        return retCon;
    }

    public void setRetCon(String retCon)
    {
        this.retCon = retCon;
    }
    
    
}

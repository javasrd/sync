package com.zc.dem.common.synmessage.analyresponse;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class MessageHeader
{
    @XmlElement(name = "Fid")
    private String fid;
    
    @XmlElement(name = "SourceSysCode")
    private String sourceSysCode;
    
    @XmlElement(name = "TargetSysCode")
    private String targetSysCode;
    
    @XmlElement(name = "MsgDate")
    private String msgDate = new Date().toString();

    public String getSourceSysCode()
    {
        return sourceSysCode;
    }

    public void setSourceSysCode(String sourceSysCode)
    {
        this.sourceSysCode = sourceSysCode;
    }

    public String getTargetSysCode()
    {
        return targetSysCode;
    }

    public void setTargetSysCode(String targetSysCode)
    {
        this.targetSysCode = targetSysCode;
    }

    public String getMsgDate()
    {
        return msgDate;
    }

    public void setMsgDate(String msgDate)
    {
        this.msgDate = msgDate;
    }

    public String getFid()
    {
        return fid;
    }

    public void setFid(String fid)
    {
        this.fid = fid;
    }
    
    
}

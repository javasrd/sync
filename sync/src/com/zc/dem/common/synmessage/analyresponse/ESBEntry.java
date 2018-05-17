package com.zc.dem.common.synmessage.analyresponse;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ESBEntry implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "MessageHeader")
    private MessageHeader messageHeader;
    
    @XmlElement(name = "MsgInfo")
    private MsgInfo msgInfo;
    
    @XmlElement(name = "RetInfo")
    private RetInfo retInfo;

    public MessageHeader getMessageHeader()
    {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader)
    {
        this.messageHeader = messageHeader;
    }

    public MsgInfo getMsgInfo()
    {
        return msgInfo;
    }

    public void setMsgInfo(MsgInfo msgInfo)
    {
        this.msgInfo = msgInfo;
    }

    public RetInfo getRetInfo()
    {
        return retInfo;
    }

    public void setRetInfo(RetInfo retInfo)
    {
        this.retInfo = retInfo;
    }
    
}

package com.zc.dem.common.synmessage.analyresponse;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

@XmlAccessorType(XmlAccessType.FIELD)
public class MsgInfo
{
    @XmlElement(name = "Msg")
    @XmlCDATA
    private List<String> msgList;

    public List<String> getMsgList()
    {
        return msgList;
    }

    public void setMsgList(List<String> msgList)
    {
        this.msgList = msgList;
    }
}

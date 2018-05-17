package com.zc.dem.common.synmessage.analyresponse.msg.yzRecord;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class YzRecordMsg
{
    @XmlElement(name = "body")
    private YzRecordBody body;
    
    @XmlElement(name = "head")
    private Header head;

    public YzRecordBody getBody()
    {
        return body;
    }

    public void setBody(YzRecordBody body)
    {
        this.body = body;
    }

    public Header getHead()
    {
        return head;
    }

    public void setHead(Header head)
    {
        this.head = head;
    }
    
    
}

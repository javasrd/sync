package com.zc.dem.common.synmessage.analyresponse.msg.yz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class YzMsg
{
    @XmlElement(name = "body")
    private YzBody body;
    
    @XmlElement(name = "head")
    private Header head;

    public YzBody getBody()
    {
        return body;
    }

    public void setBody(YzBody body)
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

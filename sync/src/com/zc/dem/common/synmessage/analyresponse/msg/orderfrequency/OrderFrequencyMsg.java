package com.zc.dem.common.synmessage.analyresponse.msg.orderfrequency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class OrderFrequencyMsg
{
    @XmlElement(name = "body")
    private OrderFrequencyBody body;
    
    @XmlElement(name = "head")
    private Header head;
    
    public OrderFrequencyBody getBody()
    {
        return body;
    }
    
    public void setBody(OrderFrequencyBody body)
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

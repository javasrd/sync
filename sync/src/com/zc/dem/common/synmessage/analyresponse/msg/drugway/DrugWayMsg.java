package com.zc.dem.common.synmessage.analyresponse.msg.drugway;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class DrugWayMsg
{
    @XmlElement(name = "body")
    private DrugWayBody body;
    
    @XmlElement(name = "head")
    private Header head;

    public DrugWayBody getBody()
    {
        return body;
    }

    public void setBody(DrugWayBody body)
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

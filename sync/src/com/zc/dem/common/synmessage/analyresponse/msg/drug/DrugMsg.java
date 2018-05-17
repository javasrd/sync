package com.zc.dem.common.synmessage.analyresponse.msg.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class DrugMsg
{
    @XmlElement(name = "body")
    private DrugBody body;
    
    @XmlElement(name = "head")
    private Header head;

    public DrugBody getBody()
    {
        return body;
    }

    public void setBody(DrugBody body)
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

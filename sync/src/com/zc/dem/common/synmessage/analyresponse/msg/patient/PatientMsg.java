package com.zc.dem.common.synmessage.analyresponse.msg.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class PatientMsg
{
    @XmlElement(name = "body")
    private PatientBody body;
    
    @XmlElement(name = "head")
    private Header head;
    
    public PatientBody getBody()
    {
        return body;
    }
    
    public void setBody(PatientBody body)
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

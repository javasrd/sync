package com.zc.dem.common.synmessage.analyresponse.msg.inpatientarea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zc.dem.common.synmessage.analyresponse.msg.Header;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class InPatientAreaMsg
{
    @XmlElement(name = "body")
    private InPatientAreaBody body;
    
    @XmlElement(name = "head")
    private Header head;

    public InPatientAreaBody getBody()
    {
        return body;
    }

    public void setBody(InPatientAreaBody body)
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

package com.zc.dem.common.synmessage.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class SoapEnvelopeReq
{
    /**
     * soap 中 envelope namespace
     */
     public static final String NAMESPACE_OF_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
    
     @XmlElement(name = "Header", namespace = NAMESPACE_OF_ENVELOPE)
     private SoapHeader header = new SoapHeader();
     
    @XmlElement(name = "Body", namespace = NAMESPACE_OF_ENVELOPE)
    private SoapBody body;
    
    public SoapBody getBody()
    {
        return body;
    }

    public void setBody(SoapBody body)
    {
        this.body = body;
    }

    public SoapHeader getHeader()
    {
        return header;
    }

    public void setHeader(SoapHeader header)
    {
        this.header = header;
    }
}

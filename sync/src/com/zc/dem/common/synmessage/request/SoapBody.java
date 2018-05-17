package com.zc.dem.common.synmessage.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SoapBody
{
    /**
     * soap 中 HIPMessageServer namespace
     */
    public static final String NAMESPACE_OF_RECEIVEB2CORDER = "http://esb.ewell.cc";
    
    @XmlElement(name = "HIPMessageServer", namespace = NAMESPACE_OF_RECEIVEB2CORDER)
    private HIPMessageServer hipMessageServer;
    
    public HIPMessageServer getHipMessageServer()
    {
        return hipMessageServer;
    }
    
    public void setHipMessageServer(HIPMessageServer hipMessageServer)
    {
        this.hipMessageServer = hipMessageServer;
    }
    
}

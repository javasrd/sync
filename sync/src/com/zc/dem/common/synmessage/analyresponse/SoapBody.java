package com.zc.dem.common.synmessage.analyresponse;

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
    
    @XmlElement(name = "HIPMessageServerResponse", namespace = NAMESPACE_OF_RECEIVEB2CORDER)
    private HIPMessageServerResponse hipMessageServerResponse;

    public HIPMessageServerResponse getHipMessageServerResponse()
    {
        return hipMessageServerResponse;
    }

    public void setHipMessageServerResponse(HIPMessageServerResponse hipMessageServerResponse)
    {
        this.hipMessageServerResponse = hipMessageServerResponse;
    }

    
}

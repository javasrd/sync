package com.zc.dem.common.synmessage.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

@XmlAccessorType(XmlAccessType.FIELD)
public class HIPMessageServer
{
    /**
     * soap 中 HIPMessageServer namespace
     */
    public static final String NAMESPACE_OF_RECEIVEB2CORDER = "http://esb.ewell.cc";
    
    @XmlElement(name = "input", namespace = NAMESPACE_OF_RECEIVEB2CORDER)
    @XmlCDATA
    private String inPut;

    public String getInPut()
    {
        return inPut;
    }

    public void setInPut(String inPut)
    {
        this.inPut = inPut;
    }

    
    
}

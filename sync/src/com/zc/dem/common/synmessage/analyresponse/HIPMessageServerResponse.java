package com.zc.dem.common.synmessage.analyresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class HIPMessageServerResponse
{
    @XmlElement(name = "output")
    private Output outPut;
    
    @XmlElement(name = "output", namespace = "http://esb.ewell.cc")
    private Output outPutYz;

    public Output getOutPut()
    {
        return outPut;
    }

    public void setOutPut(Output outPut)
    {
        this.outPut = outPut;
    }

    public Output getOutPutYz()
    {
        return outPutYz;
    }

    public void setOutPutYz(Output outPutYz)
    {
        this.outPutYz = outPutYz;
    }
}

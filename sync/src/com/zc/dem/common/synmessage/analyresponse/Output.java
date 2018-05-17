package com.zc.dem.common.synmessage.analyresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Output
{
    @XmlElement(name = "ESBEntry")
    private ESBEntry esbEntry;

    public ESBEntry getEsbEntry()
    {
        return esbEntry;
    }

    public void setEsbEntry(ESBEntry esbEntry)
    {
        this.esbEntry = esbEntry;
    }
    
    
}

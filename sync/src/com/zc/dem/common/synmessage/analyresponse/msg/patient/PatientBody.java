package com.zc.dem.common.synmessage.analyresponse.msg.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PatientBody
{
    @XmlElement(name = "row")
    private PatientRow row;
    
    public PatientRow getRow()
    {
        return row;
    }
    
    public void setRow(PatientRow row)
    {
        this.row = row;
    }
    
}

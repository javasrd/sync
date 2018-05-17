package com.zc.dem.common.synmessage.analyresponse.msg.inpatientarea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InPatientAreaBody
{
    @XmlElement(name = "row")
    private InPatientAreaRow row;

    public InPatientAreaRow getRow()
    {
        return row;
    }

    public void setRow(InPatientAreaRow row)
    {
        this.row = row;
    }
}

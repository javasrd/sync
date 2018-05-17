package com.zc.dem.common.synmessage.analyresponse.msg.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DrugBody
{
    @XmlElement(name = "row")
    private DrugRow row;

    public DrugRow getRow()
    {
        return row;
    }

    public void setRow(DrugRow row)
    {
        this.row = row;
    }
}

package com.zc.dem.common.synmessage.analyresponse.msg.drugway;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DrugWayBody
{
    @XmlElement(name = "row")
    private DrugWayRow row;

    public DrugWayRow getRow()
    {
        return row;
    }

    public void setRow(DrugWayRow row)
    {
        this.row = row;
    }
}

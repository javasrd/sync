package com.zc.dem.common.synmessage.analyresponse.msg.yz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class YzBody
{
    @XmlElement(name = "row")
    private YzRow row;

    public YzRow getRow()
    {
        return row;
    }

    public void setRow(YzRow row)
    {
        this.row = row;
    }
}

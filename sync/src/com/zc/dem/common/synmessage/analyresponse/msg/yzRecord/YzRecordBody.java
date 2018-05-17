package com.zc.dem.common.synmessage.analyresponse.msg.yzRecord;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class YzRecordBody
{
    @XmlElement(name = "row")
    private YzRecordRow row;

    public YzRecordRow getRow()
    {
        return row;
    }

    public void setRow(YzRecordRow row)
    {
        this.row = row;
    }
}

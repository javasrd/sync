package com.zc.dem.common.synmessage.analyresponse.msg.orderfrequency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderFrequencyBody
{
    @XmlElement(name = "row")
    private OrderFrequencyRow row;
    
    public OrderFrequencyRow getRow()
    {
        return row;
    }
    
    public void setRow(OrderFrequencyRow row)
    {
        this.row = row;
    }
    
}

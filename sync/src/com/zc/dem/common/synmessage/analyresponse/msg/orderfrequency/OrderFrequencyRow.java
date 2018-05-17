package com.zc.dem.common.synmessage.analyresponse.msg.orderfrequency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderFrequencyRow
{
    /**
     * 医嘱频次代码
     */
    @XmlElement(name = "Order_Frequency_Code")
    private String order_Frequency_Code;
    
    /**
     * 医嘱频次名称
     */
    @XmlElement(name = "Order_Frequency_Name")
    private String order_Frequency_Name;

    public String getOrder_Frequency_Code()
    {
        return order_Frequency_Code;
    }

    public void setOrder_Frequency_Code(String order_Frequency_Code)
    {
        this.order_Frequency_Code = order_Frequency_Code;
    }

    public String getOrder_Frequency_Name()
    {
        return order_Frequency_Name;
    }

    public void setOrder_Frequency_Name(String order_Frequency_Name)
    {
        this.order_Frequency_Name = order_Frequency_Name;
    }
    
    
}

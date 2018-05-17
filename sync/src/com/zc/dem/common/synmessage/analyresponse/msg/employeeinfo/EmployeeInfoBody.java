package com.zc.dem.common.synmessage.analyresponse.msg.employeeinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeInfoBody
{
    @XmlElement(name = "row")
    private EmployeeInfoRow row;

    public EmployeeInfoRow getRow()
    {
        return row;
    }

    public void setRow(EmployeeInfoRow row)
    {
        this.row = row;
    }
}

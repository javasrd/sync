package com.zc.dem.common.synmessage.analyresponse.msg.employeeinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeInfoRow
{
    /**
     * 编码
     */
    @XmlElement(name = "Staff_Code")
    private String staff_Code;
    
    /**
     * 名字
     */
    @XmlElement(name = "Staff_Name")
    private String staff_Name;

    public String getStaff_Code()
    {
        return staff_Code;
    }

    public void setStaff_Code(String staff_Code)
    {
        this.staff_Code = staff_Code;
    }

    public String getStaff_Name()
    {
        return staff_Name;
    }

    public void setStaff_Name(String staff_Name)
    {
        this.staff_Name = staff_Name;
    }
}

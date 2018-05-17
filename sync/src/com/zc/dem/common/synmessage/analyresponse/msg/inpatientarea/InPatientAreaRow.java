package com.zc.dem.common.synmessage.analyresponse.msg.inpatientarea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InPatientAreaRow
{
    /**
     * 编码
     */
    @XmlElement(name = "Dept_Code")
    private String dept_Code;
    
    /**
     * 名字
     */
    @XmlElement(name = "Dept_Name")
    private String dept_Name;
    
    public String getDept_Code()
    {
        return dept_Code;
    }
    
    public void setDept_Code(String dept_Code)
    {
        this.dept_Code = dept_Code;
    }
    
    public String getDept_Name()
    {
        return dept_Name;
    }
    
    public void setDept_Name(String dept_Name)
    {
        this.dept_Name = dept_Name;
    }
    
}

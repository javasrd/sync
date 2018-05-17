package com.zc.dem.common.synmessage.analyresponse.msg.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PatientRow
{
    /**
     * 住院流水号
     */
    @XmlElement(name = "Inhosp_No")
    private String inhosp_No;
    
    /**
     * 患者姓名
     */
    @XmlElement(name = "Pat_Name")
    private String pat_Name;
    
    /**
     * 生理性别代码
     */
    @XmlElement(name = "Physic_Sex_Code")
    private String physic_Sex_Code;
    
    /**
     * 病区代码
     */
    @XmlElement(name = "Ward_Code")
    private String ward_Code;
    
    /**
     * 床号
     */
    @XmlElement(name = "Bed_No")
    private String bed_No;
    
    /**
     * 状态-1：病人预出院
     */
    @XmlElement(name = "State")
    private String state;
    
    /**
     * 出生年月-格式YYYYMMDD
     */
    @XmlElement(name = "Date_Birth")
    private String date_Birth;
    
    /**
     * 病历卡号
     */
    @XmlElement(name = "Inhosp_Index_No")
    private String inhosp_Index_No;
    
    /**
     * 体重
     */
    @XmlElement(name = "Weight")
    private String weight;
    
    /**
     * 年龄
     */
    @XmlElement(name = "Age")
    private String age;
    
    /**
     * 年龄单位  D:天  M:月  Y:年
     */
    @XmlElement(name = "Age_Unit")
    private String age_Unit = "";
    
    /**
     * 预出院状态
     */
//    @XmlElement(name = "HosOutSta")
//    private String hosOutSta;
    
    /**
     * 预出院时间
     */
    @XmlElement(name = "HosOutTime")
    private String hosOutTime;
    
    public String getHosOutTime()
    {
        return hosOutTime;
    }

    public void setHosOutTime(String hosOutTime)
    {
        this.hosOutTime = hosOutTime;
    }

    public String getInhosp_No()
    {
        return inhosp_No;
    }
    
    public void setInhosp_No(String inhosp_No)
    {
        this.inhosp_No = inhosp_No;
    }
    
    public String getPat_Name()
    {
        return pat_Name;
    }
    
    public void setPat_Name(String pat_Name)
    {
        this.pat_Name = pat_Name;
    }
    
    public String getPhysic_Sex_Code()
    {
        return physic_Sex_Code;
    }
    
    public void setPhysic_Sex_Code(String physic_Sex_Code)
    {
        this.physic_Sex_Code = physic_Sex_Code;
    }
    
    public String getWard_Code()
    {
        return ward_Code;
    }
    
    public void setWard_Code(String ward_Code)
    {
        this.ward_Code = ward_Code;
    }
    
    public String getBed_No()
    {
        return bed_No;
    }
    
    public void setBed_No(String bed_No)
    {
        this.bed_No = bed_No;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }

    public String getDate_Birth()
    {
        return date_Birth;
    }

    public void setDate_Birth(String date_Birth)
    {
        this.date_Birth = date_Birth;
    }

    public String getInhosp_Index_No()
    {
        return inhosp_Index_No;
    }

    public void setInhosp_Index_No(String inhosp_Index_No)
    {
        this.inhosp_Index_No = inhosp_Index_No;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getAge_Unit()
    {
        return age_Unit;
    }

    public void setAge_Unit(String age_Unit)
    {
        this.age_Unit = age_Unit;
    }
}

package com.zc.dem.common.synmessage.analyresponse.msg.yzRecord;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "row")
public class YzRecordRow
{
    /**
     * 医嘱编码
     */
    @XmlElement(name = "RecipeID")
    private String recipeID;
    
    /**
     * 组号
     */
    @XmlElement(name = "GroupNo")
    private String groupNo;
    
    /**
     * 药单号
     */
    @XmlElement(name = "DrugListID")
    private String drugListID;
    
    /**
     * 用药频次
     */
    @XmlElement(name = "DrugFreq")
    private String drugFreq;
    
    /**
     * 药品编码
     */
    @XmlElement(name = "DrugCode")
    private String drugCode;
    
    /**
     * 药品名称
     */
    @XmlElement(name = "DrugName")
    private String drugName;
    
    /**
     * 药品数量
     */
    @XmlElement(name = "Quantity")
    private String quantity;
    
    /**
     * 数量单位
     */
    @XmlElement(name = "QuantityUnit")
    private String quantityUnit;
    
    /**
     * 执行序号
     */
    @XmlElement(name = "Schedule")
    private String schedule;
    
    /**
     * 用药时间
     */
    @XmlElement(name = "OccDT")
    private String occDT;
    
    /**
     * 收费时间
     */
    @XmlElement(name = "chargeDT")
    private String chargeDT;
    
    /**
     * 输液时间
     */
    @XmlElement(name = "InfusionDate")
    private String infusionDate;
    
    /**
     * 瓶签号/或药单唯一号
     */
    @XmlElement(name = "LabelNo")
    private String labelNo;
    
    /**
     * 药单起始时间
     */
    @XmlElement(name = "begindt")
    private String begindt;
    
    /**
     * 药单终止时间
     */
    @XmlElement(name = "enddt")
    private String enddt;

    public String getRecipeID()
    {
        return recipeID;
    }

    public void setRecipeID(String recipeID)
    {
        this.recipeID = recipeID;
    }

    public String getGroupNo()
    {
        return groupNo;
    }

    public void setGroupNo(String groupNo)
    {
        this.groupNo = groupNo;
    }

    public String getDrugListID()
    {
        return drugListID;
    }

    public void setDrugListID(String drugListID)
    {
        this.drugListID = drugListID;
    }

    public String getDrugFreq()
    {
        return drugFreq;
    }

    public void setDrugFreq(String drugFreq)
    {
        this.drugFreq = drugFreq;
    }

    public String getDrugCode()
    {
        return drugCode;
    }

    public void setDrugCode(String drugCode)
    {
        this.drugCode = drugCode;
    }

    public String getDrugName()
    {
        return drugName;
    }

    public void setDrugName(String drugName)
    {
        this.drugName = drugName;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public String getQuantityUnit()
    {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit)
    {
        this.quantityUnit = quantityUnit;
    }

    public String getSchedule()
    {
        return schedule;
    }

    public void setSchedule(String schedule)
    {
        this.schedule = schedule;
    }

    public String getOccDT()
    {
        return occDT;
    }

    public void setOccDT(String occDT)
    {
        this.occDT = occDT;
    }

    public String getChargeDT()
    {
        return chargeDT;
    }

    public void setChargeDT(String chargeDT)
    {
        this.chargeDT = chargeDT;
    }

    public String getInfusionDate()
    {
        return infusionDate;
    }

    public void setInfusionDate(String infusionDate)
    {
        this.infusionDate = infusionDate;
    }

    public String getLabelNo()
    {
        return labelNo;
    }

    public void setLabelNo(String labelNo)
    {
        this.labelNo = labelNo;
    }

    public String getBegindt()
    {
        return begindt;
    }

    public void setBegindt(String begindt)
    {
        this.begindt = begindt;
    }

    public String getEnddt()
    {
        return enddt;
    }

    public void setEnddt(String enddt)
    {
        this.enddt = enddt;
    }
    
}

package com.zc.dem.syndata.excuteRecord.bean;

import java.util.Date;

import com.zc.dem.common.dao.AbstractDO;
import com.zc.dem.common.util.Propertiesconfiguration;

/**
 *  药单执行记录
 * @author Joehart
 * @version 1.0
 */
public class ExcuteRecordBean  extends AbstractDO
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 医嘱编码
     */
    private String recipeID;
    
    /**
     * 组号
     */
    private String groupNo;
    
    /**
     * 药单号
     */
    private String drugListID;
    
    /**
     * 用药频次
     */
    private String drugFreq;
    
    /**
     * 药品编码
     */
    private String drugCode;
    
    /**
     * 药品名称
     */
    private String drugName;
    
    /**
     * 药品数量
     */
    private String quantity;
    
    /**
     * 数量单位
     */
    private String quantityUnit;
    
    /**
     * 执行序号
     */
    private String schedule;
    
    /**
     * 用药时间
     */
    private String occDT;
    
    /**
     * 收费时间
     */
    private String chargeDT;
    
    /**
     * 输液时间
     */
    private String infusionDate;
    
    /**
     * 瓶签号/或药单唯一号
     */
    private String labelNo;
    
    /**
     * 药单起始时间
     */
    private String begindt;
    
    /**
     * 药单终止时间
     */
    private String enddt;
    
    private Date synDate;
    
    /**
     * 药单全天用药次数
     */
    private String amount = "";
    
    private String pivasUser = Propertiesconfiguration.getStringProperty("pivasuser");
    
    /**
     * 对应批次表中的批次ID
     */
    private String batchID = "";
    
    /**
     * 药单执行记录状态 0：正常  1：停止 2:退费
     */
    private String state = "0";
    
    /**
     * 摆药时间
     */
    private String drugedDate;
    
    /**
     * 床号
     */
    private String bedNo;
   
    /**
     * 病人姓名
     */
    private String patName;

    public String getBedNo()
    {
        return bedNo;
    }

    public void setBedNo(String bedNo)
    {
        this.bedNo = bedNo;
    }

    public String getPatName()
    {
        return patName;
    }

    public void setPatName(String patName)
    {
        this.patName = patName;
    }

    public String getDrugedDate()
    {
        return drugedDate;
    }

    public void setDrugedDate(String drugedDate)
    {
        this.drugedDate = drugedDate;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getBatchID()
    {
        return batchID;
    }

    public void setBatchID(String batchID)
    {
        this.batchID = batchID;
    }

    public String getPivasUser()
    {
        return pivasUser;
    }

    public void setPivasUser(String pivasUser)
    {
        this.pivasUser = pivasUser;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public Date getSynDate()
    {
        return synDate;
    }

    public void setSynDate(Date synDate)
    {
        this.synDate = synDate;
    }

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

package com.zc.dem.common.synmessage.analyresponse.msg.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DrugRow
{
    /**
     * 药品代码
     */
    @XmlElement(name = "Drug_Code")
    private String drug_Code;
    
    /**
     * 药品名称
     */
    @XmlElement(name = "Drug_Name")
    private String drug_Name;
    
    /**
     * 规格
     */
    @XmlElement(name = "Specifications")
    private String specifications;
    
    /**
     * 药品使用次剂量
     */
    @XmlElement(name = "Drug_Use_One_Dosage")
    private String drug_Use_One_Dosage;
    
    /**
     * 药品使用次剂量单位
     */
    @XmlElement(name = "Drug_Use_One_Dosage_Unit")
    private String drug_Use_One_Dosage_Unit;
    
    /**
     * 拼音码
     */
    @XmlElement(name = "Pinyin_Code")
    private String pinyin_Code;
    
    /**
     * 体积
     */
    @XmlElement(name = "Drug_Volume")
    private String drug_Volume;
    
    /**
     * 体积单位
     */
    @XmlElement(name = "Drug_Volume_Unit")
    private String drug_Volume_Unit;
    
    /**
     * 包装单位
     */
    @XmlElement(name = "Drug_Packing_Unit")
    private String drug_Packing_Unit;
    
    /**
     * 皮试标志
     */
    @XmlElement(name = "Skin_Test_Flag")
    private String skin_Test_Flag;
    
    /**
     * 货架号
     */
    @XmlElement(name = "Shelf_No")
    private String shelf_No;
    
    /**
     * 药品产地
     */
    @XmlElement(name = "Drug_Place")
    private String drug_Place;
    
    /**
     * 药品产地编码
     */
    @XmlElement(name = "Drug_Place_Code")
    private String drug_Place_Code;
    
    /**
     * 价格
     */
    @XmlElement(name = "Price")
    private String price;
    
    /**
     * 高危药品标识
     */
    @XmlElement(name = "High_risk")
    private String high_risk;
    
    /**
     * 药品分类21、抗肿瘤药物 22、营养药物
     */
    @XmlElement(name = "Special_Drug")
    private String special_Drug;
    
    /**
     * 是否溶媒
     */
    private String solvent_flag = ""; 
    
    /**
     * 是否主药
     */
    private String main_drug_flag = ""; 
    
    /**
     * 有效期
     */
    private String effective_date = "";
    
    /**
     * 药品配置难度系数
     */
    private String difficulty_degree = "";

    public String getSolvent_flag()
    {
        return solvent_flag;
    }

    public void setSolvent_flag(String solvent_flag)
    {
        this.solvent_flag = solvent_flag;
    }

    public String getMain_drug_flag()
    {
        return main_drug_flag;
    }

    public void setMain_drug_flag(String main_drug_flag)
    {
        this.main_drug_flag = main_drug_flag;
    }

    public String getEffective_date()
    {
        return effective_date;
    }

    public void setEffective_date(String effective_date)
    {
        this.effective_date = effective_date;
    }

    public String getDifficulty_degree()
    {
        return difficulty_degree;
    }

    public void setDifficulty_degree(String difficulty_degree)
    {
        this.difficulty_degree = difficulty_degree;
    }

    public String getSpecial_Drug()
    {
        return special_Drug;
    }

    public void setSpecial_Drug(String special_Drug)
    {
        this.special_Drug = special_Drug;
    }

    public String getDrug_Code()
    {
        return drug_Code;
    }

    public void setDrug_Code(String drug_Code)
    {
        this.drug_Code = drug_Code;
    }

    public String getDrug_Name()
    {
        return drug_Name;
    }

    public void setDrug_Name(String drug_Name)
    {
        this.drug_Name = drug_Name;
    }

    public String getSpecifications()
    {
        return specifications;
    }

    public void setSpecifications(String specifications)
    {
        this.specifications = specifications;
    }

    public String getDrug_Use_One_Dosage()
    {
        return drug_Use_One_Dosage;
    }

    public void setDrug_Use_One_Dosage(String drug_Use_One_Dosage)
    {
        this.drug_Use_One_Dosage = drug_Use_One_Dosage;
    }

    public String getDrug_Use_One_Dosage_Unit()
    {
        return drug_Use_One_Dosage_Unit;
    }

    public void setDrug_Use_One_Dosage_Unit(String drug_Use_One_Dosage_Unit)
    {
        this.drug_Use_One_Dosage_Unit = drug_Use_One_Dosage_Unit;
    }

    public String getPinyin_Code()
    {
        return pinyin_Code;
    }

    public void setPinyin_Code(String pinyin_Code)
    {
        this.pinyin_Code = pinyin_Code;
    }

    public String getDrug_Volume()
    {
        return drug_Volume;
    }

    public void setDrug_Volume(String drug_Volume)
    {
        this.drug_Volume = drug_Volume;
    }

    public String getDrug_Volume_Unit()
    {
        return drug_Volume_Unit;
    }

    public void setDrug_Volume_Unit(String drug_Volume_Unit)
    {
        this.drug_Volume_Unit = drug_Volume_Unit;
    }

    public String getDrug_Packing_Unit()
    {
        return drug_Packing_Unit;
    }

    public void setDrug_Packing_Unit(String drug_Packing_Unit)
    {
        this.drug_Packing_Unit = drug_Packing_Unit;
    }

    public String getSkin_Test_Flag()
    {
        return skin_Test_Flag;
    }

    public void setSkin_Test_Flag(String skin_Test_Flag)
    {
        this.skin_Test_Flag = skin_Test_Flag;
    }

    public String getShelf_No()
    {
        return shelf_No;
    }

    public void setShelf_No(String shelf_No)
    {
        this.shelf_No = shelf_No;
    }

    public String getDrug_Place()
    {
        return drug_Place;
    }

    public void setDrug_Place(String drug_Place)
    {
        this.drug_Place = drug_Place;
    }

    public String getDrug_Place_Code()
    {
        return drug_Place_Code;
    }

    public void setDrug_Place_Code(String drug_Place_Code)
    {
        this.drug_Place_Code = drug_Place_Code;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getHigh_risk()
    {
        return high_risk;
    }

    public void setHigh_risk(String high_risk)
    {
        this.high_risk = high_risk;
    }
}

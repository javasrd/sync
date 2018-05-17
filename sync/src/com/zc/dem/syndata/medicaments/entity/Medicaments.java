package com.zc.dem.syndata.medicaments.entity;

import com.zc.dem.common.dao.AbstractDO;

/**
 * 药品
 * @author Joehart
 * @version 1.0
 */
public class Medicaments extends AbstractDO
{
    
    private static final long serialVersionUID = 1L;
    
    /**药品ID*/
    private Long medicamentsId;
    
    /**药品名称*/
    private String medicamentsName;
    
    /**药品编码*/
    private String medicamentsCode;
    
    /**药品分类ID*/
    private String categoryId;
    
    /**药品速查码*/
    private String medicamentsSuchama;
    
    /**药品规格*/
    private String medicamentsSpecifications;
    
    /**药品体积*/
    private String medicamentsVolume;
    
    /**药品体积单位*/
    private String medicamentsVolumeUnit;
    
    /**药品使用次剂量*/
    private String medicamentsDosage;
    
    /**药品使用次剂量单位*/
    private String medicamentsDosageUnit;
    
    /**包装单位*/
    private String medicamentsPackingUnit;
    
    /**皮试标志 0不需要,1需要*/
    private Integer medicamentsTestFlag;
    
    /**药品产地*/
    private String medicamentsPlace;
    
    /**药品产地编码*/
    private String medicamentsPlaceCode;
    
    /**单价*/
    private String medicamentsPrice;
    
    /**是否溶媒 0不是溶媒,1是溶媒*/
    private Integer medicamentsMenstruum;
    
    /**是否可用 0不可用,1可用*/
    private Integer medicamentsIsactive;
    
    /**是否做主药 0不是主药,1主药*/
    private Integer medicamentsIsmaindrug;
    
    /**
     * 是否高危
     */
    private Integer medicamentsDanger;
    
    /**
     * 货架号
     */
    private String shelfNo;   
    
    /**
     * 有效期
     */
    private String effective_date;
    
    /**
     * 药品配置难度系数
     */
    private String difficulty_degree;
    
    private int action;
    
    /**
     * 药理作用
     */
    private String phyFunctiy;

    
    public String getPhyFunctiy()
    {
        return phyFunctiy;
    }

    public void setPhyFunctiy(String phyFunctiy)
    {
        this.phyFunctiy = phyFunctiy;
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

    public String getShelfNo()
    {
        return shelfNo;
    }
    
    public void setShelfNo(String shelfNo)
    {
        this.shelfNo = shelfNo;
    }
    
    public Long getMedicamentsId()
    {
        return medicamentsId;
    }
    
    public void setMedicamentsId(Long medicamentsId)
    {
        this.medicamentsId = medicamentsId;
    }
    
    public String getMedicamentsName()
    {
        return medicamentsName;
    }
    
    public void setMedicamentsName(String medicamentsName)
    {
        this.medicamentsName = medicamentsName;
    }
    
    public String getMedicamentsCode()
    {
        return medicamentsCode;
    }
    
    public void setMedicamentsCode(String medicamentsCode)
    {
        this.medicamentsCode = medicamentsCode;
    }
    
    public String getCategoryId()
    {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }
    
    public String getMedicamentsSuchama()
    {
        return medicamentsSuchama;
    }
    
    public void setMedicamentsSuchama(String medicamentsSuchama)
    {
        this.medicamentsSuchama = medicamentsSuchama;
    }
    
    public String getMedicamentsSpecifications()
    {
        return medicamentsSpecifications;
    }
    
    public void setMedicamentsSpecifications(String medicamentsSpecifications)
    {
        this.medicamentsSpecifications = medicamentsSpecifications;
    }
    
    public String getMedicamentsVolume()
    {
        return medicamentsVolume;
    }
    
    public void setMedicamentsVolume(String medicamentsVolume)
    {
        this.medicamentsVolume = medicamentsVolume;
    }
    
    public String getMedicamentsVolumeUnit()
    {
        return medicamentsVolumeUnit;
    }
    
    public void setMedicamentsVolumeUnit(String medicamentsVolumeUnit)
    {
        this.medicamentsVolumeUnit = medicamentsVolumeUnit;
    }
    
    public String getMedicamentsDosage()
    {
        return medicamentsDosage;
    }
    
    public void setMedicamentsDosage(String medicamentsDosage)
    {
        this.medicamentsDosage = medicamentsDosage;
    }
    
    public String getMedicamentsDosageUnit()
    {
        return medicamentsDosageUnit;
    }
    
    public void setMedicamentsDosageUnit(String medicamentsDosageUnit)
    {
        this.medicamentsDosageUnit = medicamentsDosageUnit;
    }
    
    public String getMedicamentsPackingUnit()
    {
        return medicamentsPackingUnit;
    }
    
    public void setMedicamentsPackingUnit(String medicamentsPackingUnit)
    {
        this.medicamentsPackingUnit = medicamentsPackingUnit;
    }
    
    public Integer getMedicamentsTestFlag()
    {
        return medicamentsTestFlag;
    }
    
    public void setMedicamentsTestFlag(Integer medicamentsTestFlag)
    {
        this.medicamentsTestFlag = medicamentsTestFlag;
    }
    
    public String getMedicamentsPlace()
    {
        return medicamentsPlace;
    }
    
    public void setMedicamentsPlace(String medicamentsPlace)
    {
        this.medicamentsPlace = medicamentsPlace;
    }
    
    public String getMedicamentsPrice()
    {
        return medicamentsPrice;
    }
    
    public void setMedicamentsPrice(String medicamentsPrice)
    {
        this.medicamentsPrice = medicamentsPrice;
    }
    
    public Integer getMedicamentsMenstruum()
    {
        return medicamentsMenstruum;
    }
    
    public void setMedicamentsMenstruum(Integer medicamentsMenstruum)
    {
        this.medicamentsMenstruum = medicamentsMenstruum;
    }
    
    public Integer getMedicamentsIsactive()
    {
        return medicamentsIsactive;
    }
    
    public void setMedicamentsIsactive(Integer medicamentsIsactive)
    {
        this.medicamentsIsactive = medicamentsIsactive;
    }
    
    public Integer getMedicamentsIsmaindrug()
    {
        return medicamentsIsmaindrug;
    }
    
    public void setMedicamentsIsmaindrug(Integer medicamentsIsmaindrug)
    {
        this.medicamentsIsmaindrug = medicamentsIsmaindrug;
    }
    
    public Integer getMedicamentsDanger()
    {
        return medicamentsDanger;
    }
    
    public void setMedicamentsDanger(Integer medicamentsDanger)
    {
        this.medicamentsDanger = medicamentsDanger;
    }
    
    public int getAction()
    {
        return action;
    }
    
    public String getMedicamentsPlaceCode()
    {
        return medicamentsPlaceCode;
    }

    public void setMedicamentsPlaceCode(String medicamentsPlaceCode)
    {
        this.medicamentsPlaceCode = medicamentsPlaceCode;
    }

    public void setAction(int action)
    {
        this.action = action;
    }
}
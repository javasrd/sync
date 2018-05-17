package com.zc.dem.syndata.patient.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * PIVAS_PATIENT表对应实体对象
 * @author Joehart
 * @version 1.0
 */
public class PatientBean implements Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    private String gid;
    
    /**
     * 住院流水号，病人唯一标识
     */
    private String inhospNo;
    
    /**
     * 患者姓名
     */
    private String patName;
    
    /**
     * 性别：0女，1男
     */
    private Integer sex;
    
    /**
     * 病人当前病区(科室）
     */
    private String wardCode;
    
    /**
     * 病人当前状态
     */
    private String state;
    
    /**
     * 患者住院期间，所住床位对应的编号
     */
    private String bedNo;
    
    /**
     * 病人唯一住院号
     */
    private String case_ID;
    
    /**
     * 病人出生日期
     */
    private Timestamp birthDay;
    
    /**
     * 年龄
     */
    private String age;
    
    /**
     * 年龄单位，0天 1月 2年
     */
    private int ageUnit;
    
    /**
     * 0(新增)、1(变更)
     */
    private int action;
    
    /**
     * 病人体重
     */
    private String avdp;
    
    /**
     * 预出院状态
     */
    //private String hosOutSta;
    
    /**
     * 预出院时间
     */
    private String hosOutTime;
    
    /**
     * 预留字段0
     */
    private String reserve0;
    
    /**
     * 预留字段1
     */
    private String reserve1;
    
    /**
     * 预留字段2
     */
    private String reserve2;
    
    /**
     * 患者住院期间，住院对应的索引号，每次就诊相同，类似于就诊卡号
     */
    private String inhospIndexNO;
    
    /**
     * 诊断信息
     */
    private String diagnosis;
    
    public String getDiagnosis()
    {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis)
    {
        this.diagnosis = diagnosis;
    }

    public String getInhospIndexNO()
    {
        return inhospIndexNO;
    }

    public void setInhospIndexNO(String inhospIndexNO)
    {
        this.inhospIndexNO = inhospIndexNO;
    }

    public String getHosOutTime()
    {
        return hosOutTime;
    }

    public void setHosOutTime(String hosOutTime)
    {
        this.hosOutTime = hosOutTime;
    }

    public String getGid()
    {
        return gid;
    }
    
    public void setGid(String gid)
    {
        this.gid = gid;
    }
    
    public String getInhospNo()
    {
        return inhospNo;
    }
    
    public void setInhospNo(String inhospNo)
    {
        this.inhospNo = inhospNo;
    }
    
    public String getPatName()
    {
        return patName;
    }
    
    public void setPatName(String patName)
    {
        this.patName = patName;
    }
    
    public Integer getSex()
    {
        return sex;
    }
    
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
    
    public String getWardCode()
    {
        return wardCode;
    }
    
    public void setWardCode(String wardCode)
    {
        this.wardCode = wardCode;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    public String getBedNo()
    {
        return bedNo;
    }
    
    public void setBedNo(String bedNo)
    {
        this.bedNo = bedNo;
    }
    
    public String getCase_ID()
    {
        return case_ID;
    }
    
    public void setCase_ID(String caseID)
    {
        case_ID = caseID;
    }
    
    public Timestamp getBirthDay()
    {
        return birthDay;
    }
    
    public void setBirthDay(Timestamp birthDay)
    {
        this.birthDay = birthDay;
    }
    
    public String getAge()
    {
        return age;
    }
    
    public void setAge(String age)
    {
        this.age = age;
    }
    
    public int getAgeUnit()
    {
        return ageUnit;
    }
    
    public void setAgeUnit(int ageUnit)
    {
        this.ageUnit = ageUnit;
    }
    
    public int getAction()
    {
        return action;
    }
    
    public void setAction(int action)
    {
        this.action = action;
    }
    
    public String getAvdp()
    {
        return avdp;
    }
    
    public void setAvdp(String avdp)
    {
        this.avdp = avdp;
    }
    
    public String getReserve0()
    {
        return reserve0;
    }
    
    public void setReserve0(String reserve0)
    {
        this.reserve0 = reserve0;
    }
    
    public String getReserve1()
    {
        return reserve1;
    }
    
    public void setReserve1(String reserve1)
    {
        this.reserve1 = reserve1;
    }
    
    public String getReserve2()
    {
        return reserve2;
    }
    
    public void setReserve2(String reserve2)
    {
        this.reserve2 = reserve2;
    }
}

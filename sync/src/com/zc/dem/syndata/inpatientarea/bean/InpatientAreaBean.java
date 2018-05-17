package com.zc.dem.syndata.inpatientarea.bean;

import com.zc.dem.common.dao.AbstractDO;

/**
 *  病区
 * @author Joehart
 * @version 1.0
 */
public class InpatientAreaBean extends AbstractDO
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    private String gid;
    
    /**
     * 病区科室编码
     */
    private String deptCode;
    
    /**
     * 0(新增)、1(变更)
     */
    private int action;
    
    /**
     * 病区科室名称
     */
    private String deptName;
    
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
     * 病区编码
     */
    private String wardCode;
    
    /**
     * 病区名称
     */
    private String wardName;
    
    /**
     * 病区查找码，如拼音码，若有多种查找码，则以拼音码为准
     */
    private String inputCode;
    
    /**
     * 备注
     */
    private String memo;
    
    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getWardCode()
    {
        return wardCode;
    }

    public void setWardCode(String wardCode)
    {
        this.wardCode = wardCode;
    }

    public String getWardName()
    {
        return wardName;
    }

    public void setWardName(String wardName)
    {
        this.wardName = wardName;
    }

    public String getInputCode()
    {
        return inputCode;
    }

    public void setInputCode(String inputCode)
    {
        this.inputCode = inputCode;
    }

    public String getGid()
    {
        return gid;
    }
    
    public void setGid(String gid)
    {
        this.gid = gid;
    }
    
    public String getDeptCode()
    {
        return deptCode;
    }
    
    public void setDeptCode(String deptCode)
    {
        this.deptCode = deptCode;
    }
    
    public String getDeptName()
    {
        return deptName;
    }
    
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
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
    
    public int getAction()
    {
        return action;
    }
    
    public void setAction(int action)
    {
        this.action = action;
    }
    
    @Override
    public String toString()
    {
        return "InpatientAreaBean [action=" + action + ", deptCode=" + deptCode + ", deptName=" + deptName + ", gid="
            + gid + ", reserve0=" + reserve0 + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + "]";
    }
    
}

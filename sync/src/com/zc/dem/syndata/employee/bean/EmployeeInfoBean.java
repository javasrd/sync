package com.zc.dem.syndata.employee.bean;

import com.zc.dem.common.dao.AbstractDO;

/**
 *  员工信息
 * @author Joehart
 * @version 1.0
 */
public class EmployeeInfoBean extends AbstractDO
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private String gid;
    
    /**
     * 员工编码
     */
    private String staff_Code;
    
    /**
     * 员工名册
     */
    private String staff_Name;
    
    /**
     * 0(新增)、1(变更)
     */
    private int action;
    
    public String getGid()
    {
        return gid;
    }
    
    public void setGid(String gid)
    {
        this.gid = gid;
    }
    
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
    
    public int getAction()
    {
        return action;
    }
    
    public void setAction(int action)
    {
        this.action = action;
    }
    
}

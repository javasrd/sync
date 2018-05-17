package com.zc.synhis.service;

import java.util.List;

import com.zc.dem.syndata.employee.bean.EmployeeInfoBean;

/**
 * 员工信息
 * @author Joehart
 * @version 1.0
 */
public interface EmployeeInfoByHisService
{
    List<EmployeeInfoBean> qryEmployeeInfoList();
}

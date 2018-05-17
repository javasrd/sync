package com.zc.synhis.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.employee.bean.EmployeeInfoBean;

/**
 * 员工信息
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("employeeInfoByHisDAO")
public interface EmployeeInfoByHisDAO
{
    List<EmployeeInfoBean> qryEmployeeInfoList();
}

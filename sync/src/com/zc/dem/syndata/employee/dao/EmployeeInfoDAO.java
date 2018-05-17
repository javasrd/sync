package com.zc.dem.syndata.employee.dao;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.employee.bean.EmployeeInfoBean;

/**
 *  员工信息
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("employeeInfoDAO")
public interface EmployeeInfoDAO extends BaseMapper<EmployeeInfoBean, Long>
{
}

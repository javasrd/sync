package com.zc.dem.syndata.employee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.impl.BaseServiceImpl;
import com.zc.dem.syndata.employee.bean.EmployeeInfoBean;
import com.zc.dem.syndata.employee.dao.EmployeeInfoDAO;
import com.zc.dem.syndata.employee.service.EmployeeInfoService;

/**
 *  员工信息
 * @author Joehart
 * @version 1.0
 */
@Service("employeeInfoService")
public class EmployeeInfoServiceImpl extends BaseServiceImpl<EmployeeInfoBean, Long> implements EmployeeInfoService
{
    @Resource
    private EmployeeInfoDAO employeeInfoDAO;
    
    @Override
    public BaseMapper<EmployeeInfoBean, Long> getDao()
    {
        return employeeInfoDAO;
    }    
}

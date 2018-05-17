package com.zc.synhis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.employee.bean.EmployeeInfoBean;
import com.zc.synhis.dao.EmployeeInfoByHisDAO;
import com.zc.synhis.service.EmployeeInfoByHisService;

/**
 * 员工信息
 * @author Joehart
 * @version 1.0
 */
@Service("employeeInfoByHisService")
public class EmployeeInfoByHisServiceImpl implements EmployeeInfoByHisService
{
    @Resource
    private EmployeeInfoByHisDAO employeeInfoByHisDAO;
    
    @Override
    public List<EmployeeInfoBean> qryEmployeeInfoList()
    {
        return employeeInfoByHisDAO.qryEmployeeInfoList();
    }
    
}

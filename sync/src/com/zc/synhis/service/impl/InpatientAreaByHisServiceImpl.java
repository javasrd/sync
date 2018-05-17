package com.zc.synhis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;
import com.zc.synhis.dao.InpatientAreaByHisDAO;

/**
 * 病区数据DAO
 * @author Joehart
 * @version 1.0
 */
@Service("inpatientAreaByHisService")
public class InpatientAreaByHisServiceImpl implements com.zc.synhis.service.InpatientAreaByHisService
{
    @Resource
    private InpatientAreaByHisDAO inpatientAreaByHisDAO;

    @Override
    public List<InpatientAreaBean> qryInpatientAreaList()
    {
        return inpatientAreaByHisDAO.qryInpatientAreaList();
    }
   
}

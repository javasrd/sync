package com.zc.dem.syndata.inpatientarea.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;
import com.zc.dem.syndata.inpatientarea.dao.InpatientAreaDAO;
import com.zc.dem.syndata.inpatientarea.service.InpatientAreaService;

/**
 * 病区
 * @author Joehart
 * @version 1.0
 */
@Service("inpatientAreaService")
public class InpatientAreaServiceImpl implements InpatientAreaService
{
    @Resource
    private InpatientAreaDAO inpatientAreaDAO;
    
    @Autowired
    public void setInpatientAreaDAO(InpatientAreaDAO inpatientAreaDAO)
    {
        this.inpatientAreaDAO = inpatientAreaDAO;
    }

    @Override
    public void delAllSynInpatientAreas()
    {
        inpatientAreaDAO.deleteAll();
    }

    @Override
    public List<InpatientAreaBean> getInpatientAreaList()
    {
        return inpatientAreaDAO.queryAll();
    }

    @Override
    public void synData(List<InpatientAreaBean> inparentAreaList)
    {
        for (InpatientAreaBean bean : inparentAreaList)
        {
            inpatientAreaDAO.synData(bean); 
        }
    }

    @Override
    public List<InpatientAreaBean> queryInPatientAreaList()
    {
        return inpatientAreaDAO.queryInPatientAreaList();
    }
}

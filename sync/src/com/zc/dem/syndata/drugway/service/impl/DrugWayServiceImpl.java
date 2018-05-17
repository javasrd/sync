package com.zc.dem.syndata.drugway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.dem.syndata.drugway.bean.DrugWayBean;
import com.zc.dem.syndata.drugway.dao.DrugWayDAO;
import com.zc.dem.syndata.drugway.service.DrugWayService;

/**
 *  用药途径
 * @author Joehart
 * @version 1.0
 */
@Service("drugWayService")
public class DrugWayServiceImpl implements DrugWayService
{
    private DrugWayDAO drugWayDAO;
    
    @Override
    public void delAllSynDrugWays()
    {
        drugWayDAO.deleteAll();
        
    }
    
    @Override
    public List<DrugWayBean> getDrugWayList()
    {
        return drugWayDAO.queryAll();
    }
    
    @Autowired
    public void setDrugWayDAO(DrugWayDAO drugWayDAO)
    {
        this.drugWayDAO = drugWayDAO;
    }

    @Override
    public void synData(List<DrugWayBean> drugWayList)
    {
        for (DrugWayBean bean : drugWayList)
        {
            drugWayDAO.synData(bean);
        }
    }
    
}

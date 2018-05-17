package com.zc.synhis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.drugway.bean.DrugWayBean;
import com.zc.synhis.dao.DrugWayByHisDAO;
import com.zc.synhis.service.DrugWayByHisService;

/**
 * 用药途径表PIVAS_DRUGWAY对应DAO
 * @author Joehart
 * @version 1.0
 */
@Service("drugWayByHisService")
public class DrugWayByHisServiceImpl implements DrugWayByHisService
{
    @Resource
    private DrugWayByHisDAO drugWayByHisDAO;
    
    @Override
    public List<DrugWayBean> qryDrugWayList()
    {
        return drugWayByHisDAO.qryDrugWayList();
    }
    
}

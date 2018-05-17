package com.zc.synhis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.medicaments.entity.Medicaments;
import com.zc.synhis.dao.MedicamentsByHisDao;
import com.zc.synhis.service.MedicamentsByHisService;

/**
 * 药品DAO
 * @author Joehart
 * @version 1.0
 */
@Service("medicamentsByHisService")
public class MedicamentsByHisServiceImpl implements MedicamentsByHisService
{
    @Resource
    private MedicamentsByHisDao medicamentsByHisDao;
    
    @Override
    public List<Medicaments> qryMedicamentList()
    {
        return medicamentsByHisDao.qryMedicamentList();
    }
    
}

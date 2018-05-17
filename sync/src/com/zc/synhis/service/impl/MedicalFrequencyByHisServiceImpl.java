package com.zc.synhis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.medicalfrequency.entity.MedicalFrequency;
import com.zc.synhis.dao.MedicalFrequencyByHisDao;
import com.zc.synhis.service.MedicalFrequencyByHisService;

/**
 * 用药频次DAO
 * @author Joehart
 * @version 1.0
 */
@Service("medicalFrequencyByHisService")
public class MedicalFrequencyByHisServiceImpl implements MedicalFrequencyByHisService
{
    @Resource
    private MedicalFrequencyByHisDao medicalFrequencyByHisDao;

    @Override
    public List<MedicalFrequency> qryMedicalFrequencyList()
    {
        return medicalFrequencyByHisDao.qryMedicalFrequencyList();
    }
}

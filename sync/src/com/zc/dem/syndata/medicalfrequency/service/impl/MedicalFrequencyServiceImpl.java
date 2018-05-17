package com.zc.dem.syndata.medicalfrequency.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.impl.BaseServiceImpl;
import com.zc.dem.syndata.medicalfrequency.entity.MedicalFrequency;
import com.zc.dem.syndata.medicalfrequency.repository.MedicalFrequencyDao;
import com.zc.dem.syndata.medicalfrequency.service.MedicalFrequencyService;

/**
 * 用药频次
 * @author Joehart
 * @version 1.0
 */
@Service("medicalFrequencyService")
public class MedicalFrequencyServiceImpl extends
    BaseServiceImpl<MedicalFrequency, Long> implements MedicalFrequencyService
{
    @Resource
    private MedicalFrequencyDao medicalFrequencyDao;
    
    @Override
    public BaseMapper<MedicalFrequency, Long> getDao()
    {
        return medicalFrequencyDao;
    }
}

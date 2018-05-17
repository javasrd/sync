package com.zc.synhis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.patient.bean.PatientBean;
import com.zc.synhis.dao.PatientByHisDAO;
import com.zc.synhis.service.PatientByHisService;

/**
 * 病人表对应dao
 * @author Joehart
 * @version 1.0
 */
@Service("patientByHisService")
public class PatientByHisServiceImpl implements PatientByHisService
{
    @Resource
    private PatientByHisDAO patientByHisDAO;
    
    @Override
    public List<PatientBean> qryPatientList()
    {
        return patientByHisDAO.qryPatientList();
    }
    
}

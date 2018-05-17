package com.zc.dem.syndata.patient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.dem.syndata.patient.bean.PatientBean;
import com.zc.dem.syndata.patient.dao.PatientDAO;
import com.zc.dem.syndata.patient.service.PatientService;

/**
 * 病人
 *
 * @author Joehart
 * @version 1.0
 */
@Service("patientService")
public class PatientServiceImpl implements PatientService
{
    
    private PatientDAO patientDAO;
    
    @Override
    public void delAllSynPatients()
    {
        patientDAO.delAllSynPatients();
    }
    
    @Override
    public List<PatientBean> getPatientSynList()
    {
        return patientDAO.getPatientSynList();
    }
    
    @Autowired
    public void setPatientDAO(PatientDAO patientDAO)
    {
        this.patientDAO = patientDAO;
    }
    
    @Override
    public void synData(List<PatientBean> patientList)
    {
        for(PatientBean patient: patientList)
        {
            patientDAO.synData(patient);
        }
    }

}

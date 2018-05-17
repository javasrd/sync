package com.zc.dem.syndata.patient.service;

import java.util.List;

import com.zc.dem.syndata.patient.bean.PatientBean;

/**
 * 病人
 *
 * @author Joehart
 * @version 1.0
 */
public interface PatientService
{
    /***
     * 查询病人表数据
     * 
     * @return 数据
     * @exception Exception e
     * @see [类、类#方法、类#成员]
     */
    List<PatientBean> getPatientSynList();
    
    
    /**
     * 同步结束后删除所有数据
     * 
     * @see [类、类#方法、类#成员]
     */
    void delAllSynPatients();
    
    /**
     *修改表数据
     * @param bean 
     */
    void synData(List<PatientBean> patientList);
    
}

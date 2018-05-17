package com.zc.synhis.service;

import java.util.List;

import com.zc.dem.syndata.patient.bean.PatientBean;

/**
 *病人表对应dao
 * @author Joehart
 * @version 1.0
 */
public interface PatientByHisService
{
    /**
     * 病人信息列表查询(his视图)
     * 
     * @return 病人列表
     */
    List<PatientBean> qryPatientList();
}

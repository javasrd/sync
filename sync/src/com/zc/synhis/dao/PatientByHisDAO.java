package com.zc.synhis.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.patient.bean.PatientBean;

/**
 * PIVAS_PATIENT病人表对应dao
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("patientByHisDAO")
public interface PatientByHisDAO
{
    /**
     * 病人信息列表查询(his视图)
     * 
     * @return 病人列表
     */
    List<PatientBean> qryPatientList();
}

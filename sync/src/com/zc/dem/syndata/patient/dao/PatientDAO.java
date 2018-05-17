package com.zc.dem.syndata.patient.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.patient.bean.PatientBean;

/**
 * 病人
 *
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("patientDAO")
public interface PatientDAO
{
    /**
     * 病人信息列表查询
     * 
     * @return 病人列表
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
    void synData(PatientBean patient);
}

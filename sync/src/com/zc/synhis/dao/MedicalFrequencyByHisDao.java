package com.zc.synhis.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.medicalfrequency.entity.MedicalFrequency;

/**
 * 用药频次DAO
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("medicalFrequencyByHisDao")
public interface MedicalFrequencyByHisDao
{
    /**
     * 通过HIS视图获取医嘱频次
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<MedicalFrequency> qryMedicalFrequencyList();
}

package com.zc.dem.syndata.medicalfrequency.repository;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.medicalfrequency.entity.MedicalFrequency;

/**
 * 用药频次
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository
public interface MedicalFrequencyDao extends BaseMapper<MedicalFrequency, Long>
{
}

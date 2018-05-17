package com.zc.dem.syndata.medicaments.repository;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.medicaments.entity.Medicaments;

/**
 * 药品
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("medicamentsDao")
public interface MedicamentsDao extends BaseMapper<Medicaments, Long>
{
}

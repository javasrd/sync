package com.zc.synhis.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.medicaments.entity.Medicaments;

/**
 * 药品DAO
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("medicamentsByHisDao")
public interface MedicamentsByHisDao
{
    List<Medicaments> qryMedicamentList();
}

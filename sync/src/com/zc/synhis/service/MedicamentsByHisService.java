package com.zc.synhis.service;

import java.util.List;

import com.zc.dem.syndata.medicaments.entity.Medicaments;

/**
 *  药品DAO
 * @author Joehart
 * @version 1.0
 */
public interface MedicamentsByHisService
{
    List<Medicaments> qryMedicamentList();
}

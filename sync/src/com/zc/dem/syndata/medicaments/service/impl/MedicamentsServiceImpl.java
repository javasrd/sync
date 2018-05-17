package com.zc.dem.syndata.medicaments.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.impl.BaseServiceImpl;
import com.zc.dem.syndata.medicaments.entity.Medicaments;
import com.zc.dem.syndata.medicaments.repository.MedicamentsDao;
import com.zc.dem.syndata.medicaments.service.MedicamentsService;

/**
 * 药品
 * @author Joehart
 * @version 1.0
 */
@Service("medicamentsService")
public class MedicamentsServiceImpl extends BaseServiceImpl<Medicaments, Long>
    implements MedicamentsService
{
    @Resource
    private MedicamentsDao medicamentsDao;
    
    @Override
    public BaseMapper<Medicaments, Long> getDao()
    {
        return medicamentsDao;
    }

}

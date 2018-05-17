package com.zc.dem.syndata.yz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.common.service.impl.BaseServiceImpl;
import com.zc.dem.syndata.yz.bean.SynYzBean;
import com.zc.dem.syndata.yz.dao.SynYzDAO;
import com.zc.dem.syndata.yz.service.SynYzService;

/**
 * 医嘱Service实现
 * @author Joehart
 * @version 1.0
 */
@Service("synYzService")
public class SynYzServiceImpl extends BaseServiceImpl<SynYzBean, Long> implements SynYzService
{
    
    @Resource
    private SynYzDAO synYzDAO;
    
    @Override
    public BaseMapper<SynYzBean, Long> getDao()
    {
        return synYzDAO;
    }
    
    @Override
    public List<SynYzBean> queryYZForHisDel()
    {
        return synYzDAO.queryYZForHisDel();
    }
    
    @Override
    public void updateYzByCondition(SynYzBean bean)
    {
        synYzDAO.updateYzByCondition(bean);
    }
    
    @Override
    public void checkYzStatus()
    {
        synYzDAO.checkYzStatus();
    }

    @Override
    public List<SynYzBean> qryYzList(Map<String, Object> map)
    {
        return synYzDAO.qryYzList(map);
    }
}

package com.zc.synhis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.syndata.yz.bean.SynYzBean;
import com.zc.synhis.dao.SynYzByHisDAO;
import com.zc.synhis.service.SynYzByHisService;

/**
 * @author Joehart
 * @version 1.0
 */
@Service("synYzByHisService")
public class SynYzByHisServiceImpl implements SynYzByHisService
{
    @Resource
    private SynYzByHisDAO synYzByHisDAO;
    
    @Override
    public List<SynYzBean> qryYzList(Map<String, Object> map)
    {
        return synYzByHisDAO.qryYzList(map);
    }
    
}

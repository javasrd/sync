package com.zc.dem.backup.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zc.dem.backup.bean.ServerNodeBean;
import com.zc.dem.backup.dao.BackDAO;
import com.zc.dem.backup.service.ServerNodeService;


@Service("ServerNodeService")
public class ServerNodeServiceImpl implements ServerNodeService
{

    @Resource
    private BackDAO backDAO;
    
    @Override
    public ServerNodeBean getServerNode(String ip,String pivasUser)
    {
       return backDAO.getServerNode(ip,pivasUser);
    }
    
}
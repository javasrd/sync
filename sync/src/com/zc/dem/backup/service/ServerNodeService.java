package com.zc.dem.backup.service;

import com.zc.dem.backup.bean.ServerNodeBean;

public interface ServerNodeService
{
    public ServerNodeBean getServerNode(String ip,String pivasuser);
}
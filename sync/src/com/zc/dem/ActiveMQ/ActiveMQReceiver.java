package com.zc.dem.ActiveMQ;

import org.apache.log4j.Logger;
public class ActiveMQReceiver
{
    Logger log = Logger.getLogger(ActiveMQReceiver.class);
    public void receive(SyncData syncData)
    {
        log.info("server端收到消息：" + syncData.getData());
    }
}

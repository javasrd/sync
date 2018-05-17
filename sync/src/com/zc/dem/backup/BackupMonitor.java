package com.zc.dem.backup;
import javax.annotation.Resource;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.zc.dem.backup.bean.ServerNodeBean;
import com.zc.dem.backup.service.ServerNodeService;
import com.zc.dem.common.StartUpServlet;
import com.zc.dem.common.util.Propertiesconfiguration;

/**
 * 短信/邮件发送
 * @author Joehart
 * @version 1.0
 */
@Service("BackupMonitor")
public class BackupMonitor
{
    @Resource
    private ServerNodeService serverNodeService;
    
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(BackupMonitor.class);
    
    private static Integer preFlag = -1;
    
    public BackupMonitor()
    {
        
    }
    
    public void ExecuteService()
    {
        //获取变更 0->1暂停 1->0继续
        //读取本地IP
        String ipStr = Propertiesconfiguration.getStringProperty("localip"); 
        String pivasuser = Propertiesconfiguration.getStringProperty("pivasuser"); 
        if(ipStr == null)
        {
            return;
        }

        Scheduler scheduler = StartUpServlet.scheduler;
        
        if(null == scheduler)
        {
            return;
        }
        
        if(serverNodeService != null)
        {
            ServerNodeBean bean = serverNodeService.getServerNode(ipStr,pivasuser);
            if(bean != null)
            {
                try
                {
                    if(preFlag != bean.getFlag())
                    {
                        log.info("Flag changed:preFlag:" + preFlag + " newFlag:" + bean.getFlag());
                        preFlag = bean.getFlag();
                    }
                    
                    if(bean.getFlag() == 1)
                    {
                        scheduler.pauseAll();
                    }
                    else
                    {
                        scheduler.resumeAll();
                    }
                }
                catch(org.quartz.SchedulerException e)
                {
                    log.error("schedule use failed;" + e.getMessage());
                }
            }
        }
    }


    

}

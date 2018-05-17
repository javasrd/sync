package com.zc.dem.ActiveMQ;

import javax.annotation.Resource;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

/**
 * 发送MQ消息方法
 *
 * @author Joehart
 * @version 1.0
 */
public class ActiveMQSender
{
    @Resource
    private ActiveMQQueue destPatient;
    
    @Resource
    private ActiveMQQueue destMedicamentDict;
    
    @Resource
    private ActiveMQQueue destInpatientArea;
    
    @Resource
    private ActiveMQQueue destDrugway;
    
    @Resource
    private ActiveMQQueue destDrugfrequency;
    
    @Resource
    private ActiveMQQueue destDrugAdministration;
    
    @Resource
    private ActiveMQQueue destSyncDataScheduleTask;
    
    @Resource
    private ActiveMQQueue destEmployeeInfo;
    
    @Resource
    private ActiveMQQueue destYdExcuteRecord;
    
    @Resource
    private JmsTemplate jmsTemplate;
    
    //往病人队列发送消息
    public void sendPatientMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destPatient, product);
    }
    
    //往药品字典发送消息
    public void sendMedicamentDictMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destMedicamentDict, product);
    }
    
    //往病人区域发送消息
    public void sendInpatientAreaMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destInpatientArea, product);
    }
    
    //往员工区域发送消息
    public void sendEmployeeInfoMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destEmployeeInfo, product);
    }
    
    //往医嘱队列发送消息
    public void sendDrugwayMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destDrugway, product);
    }
    
    //往医嘱频次 队列发送消息
    public void sendDrugfrequencyMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destDrugfrequency, product);
    }
    
    //往用药途径队列发送消息
    public void sendDrugAdministrationMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destDrugAdministration, product);
    }
    
    //往内部同步数据任务队列发送消息
    public void sendSyncDataScheduleTaskMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destSyncDataScheduleTask, product);
    }
    
    //往用药单执行记录列发送消息
    public void sendYdExcuteRecordMessage(SyncData product)
    {
        this.getJmsTemplate().convertAndSend(destYdExcuteRecord, product);
    }

    public ActiveMQQueue getDestPatient()
    {
        return destPatient;
    }
    
    public void setDestPatient(ActiveMQQueue destPatient)
    {
        this.destPatient = destPatient;
    }
    
    public ActiveMQQueue getDestMedicamentDict()
    {
        return destMedicamentDict;
    }
    
    public void setDestMedicamentDict(ActiveMQQueue destMedicamentDict)
    {
        this.destMedicamentDict = destMedicamentDict;
    }
    
    public ActiveMQQueue getDestInpatientArea()
    {
        return destInpatientArea;
    }
    
    public void setDestInpatientArea(ActiveMQQueue destInpatientArea)
    {
        this.destInpatientArea = destInpatientArea;
    }
    
    public ActiveMQQueue getDestDrugway()
    {
        return destDrugway;
    }
    
    public void setDestDrugway(ActiveMQQueue destDrugway)
    {
        this.destDrugway = destDrugway;
    }
    
    public ActiveMQQueue getDestDrugfrequency()
    {
        return destDrugfrequency;
    }
    
    public void setDestDrugfrequency(ActiveMQQueue destDrugfrequency)
    {
        this.destDrugfrequency = destDrugfrequency;
    }
    
    public ActiveMQQueue getDestDrugAdministration()
    {
        return destDrugAdministration;
    }
    
    public void setDestDrugAdministration(ActiveMQQueue destDrugAdministration)
    {
        this.destDrugAdministration = destDrugAdministration;
    }
    
    public ActiveMQQueue getDestSyncDataScheduleTask()
    {
        return destSyncDataScheduleTask;
    }
    
    public void setDestSyncDataScheduleTask(ActiveMQQueue destSyncDataScheduleTask)
    {
        this.destSyncDataScheduleTask = destSyncDataScheduleTask;
    }

    public JmsTemplate getJmsTemplate()
    {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }
}

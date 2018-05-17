package com.zc.dem.schedulejob.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zc.dem.common.RestfulRetCodeConfiguration;
import com.zc.dem.common.StartUpServlet;
import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.schedulejob.entity.ScheduleJob;
import com.zc.dem.schedulejob.repository.ScheduleJobDao;
import com.zc.dem.schedulejob.service.TaskManagerService;

/**
 * @author Joehart
 * @version 1.0
 */
@Service("taskManagerService")
public class TaskManagerServiceImpl implements TaskManagerService, DemConstant
{
    @Resource
    ScheduleJobDao scheduleJobDao;
    
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(TaskManagerServiceImpl.class);
    
    private String retCode;
    private String retMsg;

    private static HashMap<Integer, String> taskGroup = new HashMap<Integer, String>()
    {
        {
            put(actionType.patient, "patient");
            put(actionType.drugAdministration, "drugAdministration");
            put(actionType.medicamentDict, "medicamentDict");
            put(actionType.inpatientArea, "inpatientArea");
            put(actionType.drugfrequency, "drugfrequency");
            put(actionType.drugway, "drugway");
            put(actionType.interSyncTask, "interSyncTask");
            put(actionType.excuteRecord, "excuteRecord");
            put(actionType.employee, "employee");
        }
    };
    
    private static HashMap<Integer, String> triggerGroup = new HashMap<Integer, String>()
    {
        {
            put(actionType.patient, "patient");
            put(actionType.drugAdministration, "drugAdministration");
            put(actionType.medicamentDict, "medicamentDict");
            put(actionType.inpatientArea, "inpatientArea");
            put(actionType.drugfrequency, "drugfrequency");
            put(actionType.drugway, "drugway");
            put(actionType.interSyncTask, "interSyncTask");
            put(actionType.excuteRecord, "excuteRecord");
            put(actionType.employee, "employee");
        }
    };

    @Override
    public Map addJob(ScheduleJob schedulejob)
    {
        if (schedulejob == null)
        {
            retCode = "201";
            retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
            return genRetMap(retCode, retMsg, null);
        }
        
        //先检查一遍任务相同名字的任务是否已经存在
        for (ScheduleJob job : StartUpServlet.jobList)
        {
            if (job.getJobName().equals(schedulejob.getJobName()))
            {
                retCode = "201";
                retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
                return genRetMap(retCode, retMsg, null);
            }
        }
        
        //首先把任务信息增加到数据库中
        scheduleJobDao.insertOneTask(schedulejob);
        ScheduleJob newScheduleJob = scheduleJobDao.selectOneTask(schedulejob);
        
        try
        {
            //如果不存在，且任务状态为启用，则重新创建任务
            if (schedulejob.getJobEnable().equals(ScheduleJob.TASK_ENABLE))
            {
                createJob(newScheduleJob);
                
                //添加到任务队列
                StartUpServlet.jobList.add(newScheduleJob);
            }
        }
        catch (SchedulerException e)
        {
            retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
            scheduleJobDao.deleteOneTask(newScheduleJob);
            log.error(e.toString());
            return genRetMap(retCode, retMsg, newScheduleJob);
        }
        
        retCode = "200";
        retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
        return genRetMap(retCode, retMsg, newScheduleJob);
    }
    
    @Override
    public void createJob(ScheduleJob schedulejob)
        throws SchedulerException
    {
        Class clazz = null;
        try
        {
            clazz = Class.forName(schedulejob.getBeanClass());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
        //创建任务
        TriggerKey triggerKey = TriggerKey.triggerKey(schedulejob.getJobName(), taskGroup.get(schedulejob.getAction()));
        
        SimpleTrigger trigger = (SimpleTrigger)StartUpServlet.scheduler.getTrigger(triggerKey);
        
        JobDetail jobDetail =
            JobBuilder.newJob(clazz)
                .withIdentity(schedulejob.getJobName(), taskGroup.get(schedulejob.getAction()))
                .build();
        
        jobDetail.getJobDataMap().put("schedulejob", schedulejob);
        
        if (schedulejob.getJobType().equals(DemConstant.taskType.schedule))
        {
            trigger =
                (SimpleTrigger)TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .forJob(jobDetail)
                    .startAt(schedulejob.getStartTime())
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(genIntevalMinutes(schedulejob)))
                    .build();
        }
        else
        {
            trigger =
                (SimpleTrigger)TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .forJob(jobDetail)
                    .startAt(schedulejob.getStartTime())
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForTotalCount(1))
                    .build();
        }
        
        StartUpServlet.scheduler.scheduleJob(jobDetail, trigger);
    }
    
    @Override
    public Map startJob(ScheduleJob schedulejob)
    {
        try
        {
            //立即启动一个job
            JobKey jobkey = JobKey.jobKey(schedulejob.getJobName(), taskGroup.get(schedulejob.getAction()));
            
            if (!StartUpServlet.scheduler.checkExists(jobkey))
            {
                retCode = "201";
                retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
                return genRetMap(retCode, retMsg, schedulejob);
            }
            
            //找到正在允许的任务
            List<JobExecutionContext> executingJobs = StartUpServlet.scheduler.getCurrentlyExecutingJobs();
            
            //任务正在运行，启动失败
            for (JobExecutionContext job : executingJobs)
            {
                if (job.getJobDetail().getKey().getName().equals(schedulejob.getJobName())
                    && job.getJobDetail().getKey().getGroup().equals(taskGroup.get(schedulejob.getAction())))
                {
                    retCode = "201";
                    retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
                    return genRetMap(retCode, retMsg, schedulejob);
                }
            }

            StartUpServlet.scheduler.triggerJob(jobkey);
        }
        catch(SchedulerException e)
        {
            retCode = "201";
            retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
            log.error(e.toString());
            return genRetMap(retCode, retMsg, schedulejob);
        }

        retCode = "200";
        retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
        return genRetMap(retCode, retMsg, schedulejob);
    }
    
    @Override
    public Map deleteJob(ScheduleJob schedulejob)
    {
        try
        {
            //根据jobID找到任务名字
            ScheduleJob myscheduleJob = scheduleJobDao.selectOneTask(schedulejob);
            
            if (myscheduleJob != null)
            {
                //删除一个job
                JobKey jobkey = JobKey.jobKey(myscheduleJob.getJobName(), taskGroup.get(myscheduleJob.getAction()));
                
                if (!StartUpServlet.scheduler.checkExists(jobkey) && myscheduleJob.getJobEnable() == 0)
                {
                    retCode = "201";
                    retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
                    return genRetMap(retCode, retMsg, myscheduleJob);
                }
                else
                {
                    scheduleJobDao.deleteOneTask(myscheduleJob);
                    StartUpServlet.scheduler.deleteJob(jobkey);
                    
                    //删除原来队列中的元素
                    Iterator<ScheduleJob> iter = StartUpServlet.jobList.iterator();
                    while (iter.hasNext())
                    {
                        ScheduleJob job = iter.next();
                        if (job.getJobName().equals(myscheduleJob.getJobName()))
                        {
                            iter.remove();
                        }
                    }
                }
            }
        }
        catch (SchedulerException e)
        {
            retCode = "201";
            retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
            log.error(e.toString());
            return genRetMap(retCode, retMsg, schedulejob);
        }
        
        retCode = "200";
        retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
        return genRetMap(retCode, retMsg, schedulejob);
    }
    
    @Override
    public Map modifyJob(ScheduleJob schedulejob)
    {
        // 根据jobid,获取原始action
        try
        {
            TriggerKey triggerKey =
                TriggerKey.triggerKey(schedulejob.getJobName(), taskGroup.get(schedulejob.getActionBefore()));
            
            SimpleTrigger trigger = (SimpleTrigger)StartUpServlet.scheduler.getTrigger(triggerKey);
            
            //先删除 后创建
            //任务存在 
            if (trigger != null)
            {
                JobKey jobkey = JobKey.jobKey(schedulejob.getJobName(), taskGroup.get(schedulejob.getActionBefore()));
                StartUpServlet.scheduler.deleteJob(jobkey);
                
                //删除原来队列中的元素
                Iterator<ScheduleJob> iter = StartUpServlet.jobList.iterator();
                while (iter.hasNext())
                {
                    ScheduleJob job = iter.next();
                    if (job.getJobName().equals(schedulejob.getJobName()))
                    {
                        iter.remove();
                    }
                }
            }
            
            //重新创建任务
            if (schedulejob.getJobEnable().equals(ScheduleJob.TASK_ENABLE))
            {
                createJob(schedulejob);
                StartUpServlet.jobList.add(schedulejob);
            }
        }
        catch (SchedulerException e)
        {
            retCode = "201";
            retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
            log.error(e.toString());
            return genRetMap(retCode, retMsg, schedulejob);
        }
        
        //操作成功之后修改数据库数据
        scheduleJobDao.updateOneTask(schedulejob);

        retCode = "200";
        retMsg = RestfulRetCodeConfiguration.getStringProperty(retCode);
        return genRetMap(retCode, retMsg, schedulejob);
    }
    
    private Integer genIntevalMinutes(ScheduleJob schedulejob)
    {
        Integer minuteInteval = 0;
        
        if (schedulejob.getJobType().equals(DemConstant.taskType.schedule))
        {
            if (schedulejob.getExecMode().equals(DemConstant.execMode.minute))
            {
                minuteInteval = schedulejob.getRunInterval();
            }
            else if (schedulejob.getExecMode().equals(DemConstant.execMode.day))
            {
                minuteInteval = schedulejob.getRunInterval() * 24 * 60;
            }
        }

        return minuteInteval;
    }
    
    private Map<String, String> genRetMap(String retCode, String retMsg, ScheduleJob schedulejob)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DemConstant.response.retCode, retCode);
        map.put(DemConstant.response.retMsg, retMsg);
        
        if (schedulejob != null)
        {
            map.put(DemConstant.response.scheduleId, String.valueOf(schedulejob.getJobId()));
        }
        else
        {
            map.put(DemConstant.response.scheduleId, "");
        }
        
        return map;
    }
    
    public boolean isRunning(ScheduleJob schedulejob)
    {
        boolean isRunning = false;
        try
        {
            //找到正在允许的任务
            List<JobExecutionContext> executingJobs = StartUpServlet.scheduler.getCurrentlyExecutingJobs();
            
            //任务正在运行，启动失败
            for (JobExecutionContext job : executingJobs)
            {
                if (job.getJobDetail().getKey().getName().equals(schedulejob.getJobName())
                    && job.getJobDetail().getKey().getGroup().equals(taskGroup.get(schedulejob.getAction())))
                {
                    isRunning = true;
                }
            }
            
        }
        catch (SchedulerException e)
        {
            log.error("Query task running states failed.");
        }
        
        return isRunning;
    }
}

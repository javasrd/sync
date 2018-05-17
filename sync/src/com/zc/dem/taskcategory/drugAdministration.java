package com.zc.dem.taskcategory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zc.dem.common.RestfulRetCodeConfiguration;
import com.zc.dem.common.StartUpServlet;
import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.util.InvokeSystemCmd;
import com.zc.dem.common.util.Propertiesconfiguration;
import com.zc.dem.schedulejob.entity.ScheduleJob;
import com.zc.dem.schedulejob.entity.ScheduleResult;
import com.zc.dem.syndata.yz.bean.SynYzBean;

/**
 * 医嘱任务具体的执行
 * @author Joehart
 * @version 1.0
 */
@DisallowConcurrentExecution
public class drugAdministration implements Job
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(drugAdministration.class);
    
    /**
     * 同步模式：0ETL
     */
    private static final String SYNCHRONIZATION_MODE_ETL = "0";
    
    /**
     * 同步模式：0ETL 1HIS 2VIEW
     */
    private static final String SYNCHRONIZATION_MODE_VIEW = "2";
    
    @Override
    public void execute(JobExecutionContext context)
        throws JobExecutionException
    {
        log.info("drugAdministration execute");
        // 同步模式
        String synchronizationMode = Propertiesconfiguration.getStringProperty("synhis.synchronizationmode");
        
        //获取重试次数
        ScheduleJob scheduleJob = (ScheduleJob)context.getJobDetail().getJobDataMap().get("schedulejob");
        
        if (scheduleJob == null)
        {
            scheduleJob = new ScheduleJob();
            scheduleJob.setRetryTime(3);
        }
        
        //每次执行都记录执行结果到数据库中
        ScheduleResult scheduleResult = new ScheduleResult();
        scheduleResult.setTaskID(scheduleJob.getJobId());
        scheduleResult.setTaskName(scheduleJob.getJobName());
        scheduleResult.setTaskType(scheduleJob.getJobType());
        scheduleResult.setTaskContent(scheduleJob.getAction());
        scheduleResult.setTaskExecStartTime(new Date());
        
        // 判断当前同步模式
        if (SYNCHRONIZATION_MODE_ETL.equals(synchronizationMode))
        {
            synDataByETL(context, scheduleJob, scheduleResult);
        }
        else if (SYNCHRONIZATION_MODE_VIEW.equals(synchronizationMode))
        {
            try
            {
                List<SynYzBean> yzList = StartUpServlet.synYzByHisService.qryYzList(null);
                
                if (yzList != null && yzList.size() > 0)
                {
                    for(SynYzBean bean : yzList)
                    {
                        if(StringUtils.isNotEmpty(bean.getDrugUseOneDosAge()) && bean.getDrugUseOneDosAge().startsWith("."))
                        {
                            bean.setDrugUseOneDosAge("0" + bean.getDrugUseOneDosAge());
                        }
                        
                        if(StringUtils.isNotEmpty(bean.getDrugAmount()) && bean.getDrugAmount().startsWith("."))
                        {
                            bean.setDrugAmount("0" + bean.getDrugAmount());
                        }
                    }
                    
                    StartUpServlet.synYzService.synData(yzList);
                }
                successSyn(context, scheduleResult);
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
                retrySyn(context, scheduleJob, scheduleResult);
            }
        }
        else
        {
            try
            {
                synDataForSZ(context, scheduleJob, scheduleResult);
            }
            catch (HttpException e)
            {
                log.error(e.getMessage(), e);
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
        
    }
    
    /**
     * 某医院数据同步
     * <一句话功能简述>
     * <功能详细描述>
     * @param context
     * @param scheduleJob
     * @param scheduleResult
     * @throws JobExecutionException
     * @throws IOException 
     * @throws HttpException 
     * @see [类、类#方法、类#成员]
     */
    private void synDataForSZ(JobExecutionContext context, ScheduleJob scheduleJob, ScheduleResult scheduleResult)
        throws JobExecutionException, HttpException, IOException
    {
        boolean result = false;
        try
        {
            result = StartUpServlet.getSynDataForSZ.synAdvice();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            retrySyn(context, scheduleJob, scheduleResult);
        }
        //执行失败重试
        if (!result)
        {
            retrySyn(context, scheduleJob, scheduleResult);
        }
        else
        {
            successSyn(context, scheduleResult);
        }
    }
    
    /**
     * 获取同步数据通过ETL
     * <一句话功能简述>
     * <功能详细描述>
     * @param context
     * @param scheduleJob
     * @param scheduleResult
     * @throws JobExecutionException
     * @see [类、类#方法、类#成员]
     */
    private void synDataByETL(JobExecutionContext context, ScheduleJob scheduleJob, ScheduleResult scheduleResult)
        throws JobExecutionException
    {
        //获取对应任务的脚本文件路径
        String patientShell =
            RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.drugAdministration);
        
        if (patientShell.length() > 0 && patientShell != null)
        {
            if (patientShell.length() > 0 && patientShell != null)
            {
                //执行失败重试
                if (InvokeSystemCmd.exeSysCommand(patientShell) != DemConstant.operateResult.suc)
                {
                    retrySyn(context, scheduleJob, scheduleResult);
                }
                else
                {
                    successSyn(context, scheduleResult);
                }
            }
        }
    }
    
    /**
     * 同步成功
     * <一句话功能简述>
     * <功能详细描述>
     * @param context
     * @param scheduleResult
     * @see [类、类#方法、类#成员]
     */
    private void successSyn(JobExecutionContext context, ScheduleResult scheduleResult)
    {
        scheduleResult.setTaskExecStopTime(new Date());
        scheduleResult.setTaskResult(DemConstant.operateResult.suc);
        StartUpServlet.scheduleJobDao.insertRecord(scheduleResult);
        context.put("retryTimes", 0);
    }
    
    /**
     * 失败重试
     * <一句话功能简述>
     * <功能详细描述>
     * @param context
     * @param scheduleJob
     * @param scheduleResult
     * @throws JobExecutionException
     * @see [类、类#方法、类#成员]
     */
    private void retrySyn(JobExecutionContext context, ScheduleJob scheduleJob, ScheduleResult scheduleResult)
        throws JobExecutionException
    {
        scheduleResult.setTaskExecStopTime(new Date());
        scheduleResult.setTaskResult(DemConstant.operateResult.fasle);
        StartUpServlet.scheduleJobDao.insertRecord(scheduleResult);
        
        Integer hasRetryTimes = (Integer)context.get("retryTimes");
        if (hasRetryTimes == null || hasRetryTimes < scheduleJob.getRetryTime())
        {
            context.put("retryTimes", (hasRetryTimes == null || hasRetryTimes == 0) ? 1 : hasRetryTimes + 1);
            JobExecutionException jobExeException = new JobExecutionException();
            waitForRetryIntervalTime(scheduleJob);
            jobExeException.setRefireImmediately(true);
            throw jobExeException;
        }
        else if (hasRetryTimes != null && hasRetryTimes >= scheduleJob.getRetryTime())
        {
            context.put("retryTimes", 0);
        }
    }
    
    private void waitForRetryIntervalTime(ScheduleJob scheduleJob)
    {
        try
        {
            Thread.currentThread().sleep(scheduleJob.getRetryInterval() * 1000);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}

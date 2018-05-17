package com.zc.dem.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zc.dem.ActiveMQ.ActiveMQSender;
import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.util.GetSynDataForSZ;
import com.zc.dem.common.util.SetPushToMQMessage;
import com.zc.dem.schedulejob.entity.ScheduleJob;
import com.zc.dem.schedulejob.repository.ScheduleJobDao;
import com.zc.dem.schedulejob.service.TaskManagerService;
import com.zc.dem.syndata.drugway.service.DrugWayService;
import com.zc.dem.syndata.employee.service.EmployeeInfoService;
import com.zc.dem.syndata.excuteRecord.service.SynYdRecordService;
import com.zc.dem.syndata.inpatientarea.service.InpatientAreaService;
import com.zc.dem.syndata.medicalfrequency.service.MedicalFrequencyService;
import com.zc.dem.syndata.medicaments.service.MedicamentsService;
import com.zc.dem.syndata.patient.service.PatientService;
import com.zc.dem.syndata.yz.service.SynYzService;
import com.zc.synhis.service.DrugWayByHisService;
import com.zc.synhis.service.EmployeeInfoByHisService;
import com.zc.synhis.service.InpatientAreaByHisService;
import com.zc.synhis.service.MedicalFrequencyByHisService;
import com.zc.synhis.service.MedicamentsByHisService;
import com.zc.synhis.service.PatientByHisService;
import com.zc.synhis.service.SynYdRecordByHisService;
import com.zc.synhis.service.SynYzByHisService;

/**
 * BLA启动类
 * @author Joehart
 * @version 1.0
 */
public class StartUpServlet extends HttpServlet
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1757356289230614629L;
    
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(StartUpServlet.class);

    public static ArrayList<ScheduleJob> jobList = null;
    
    public static Scheduler scheduler = null;
    
    public static ScheduleJobDao scheduleJobDao;
    
    public static ActiveMQSender queueProducer;
    
    public static SetPushToMQMessage setPushToMQMessage;
    
    /**
     * 医嘱同步
     */
    public static SynYzService synYzService;
    
    public static GetSynDataForSZ getSynDataForSZ;
    
    /**
     * 药单执行记录同步
     */
    public static SynYdRecordService synYdRecordService;
    
    /**
     * 病区同步
     */
    public static InpatientAreaService inpatientAreaService;
    
    /**
     * 医嘱频次
     */
    public static MedicalFrequencyService medicalFrequencyService;
    
    /**
     * 用药途径
     */
    public static DrugWayService drugWayService;
    
    /**
     * 药品
     */
    public static MedicamentsService medicamentsService;
    
    /**
     * 病区员工
     */
    public static EmployeeInfoService employeeInfoService;
    
    /**
     * 病人
     */
    public static PatientService patientService;
    
    /**
     * 医嘱同步（视图）
     */
    public static SynYzByHisService synYzByHisService;
    
    /**
     * 药单执行记录同步（视图）
     */
    public static SynYdRecordByHisService synYdRecordByHisService;
    
    /**
     * 病区同步（视图）
     */
    public static InpatientAreaByHisService inpatientAreaByHisService;
    
    /**
     * 医嘱频次（视图）
     */
    public static MedicalFrequencyByHisService medicalFrequencyByHisService;
    
    /**
     * 用药途径（视图）
     */
    public static DrugWayByHisService drugWayByHisService;
    
    /**
     * 药品（视图）
     */
    public static MedicamentsByHisService medicamentsByHisService;
    
    /**
     * 病区员工（视图）
     */
    public static EmployeeInfoByHisService employeeInfoByHisService;
    
    /**
     * 病人（视图）
     */
    public static PatientByHisService patientByHisService;

    @Override
    public void init()
        throws ServletException
    {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        
        scheduleJobDao = (ScheduleJobDao)wac.getBean("scheduleJobDao");
        
        TaskManagerService taskManagerService = (TaskManagerService)wac.getBean("taskManagerService");
        
        scheduler = (Scheduler)wac.getBean("schedulerFactoryBean");
        
        queueProducer = (ActiveMQSender)wac.getBean("queueProducer");
        
        setPushToMQMessage = (SetPushToMQMessage)wac.getBean("setPushToMQMessage");
        
        synYzService = (SynYzService)wac.getBean("synYzService");
        
        getSynDataForSZ = (GetSynDataForSZ)wac.getBean("getSynDataForSZ");
        
        synYdRecordService = (SynYdRecordService)wac.getBean("synYdRecordService");

        inpatientAreaService = (InpatientAreaService)wac.getBean("inpatientAreaService");
        
        medicalFrequencyService = (MedicalFrequencyService)wac.getBean("medicalFrequencyService");
        
        drugWayService = (DrugWayService)wac.getBean("drugWayService");
        
        medicamentsService = (MedicamentsService)wac.getBean("medicamentsService");
        
        employeeInfoService = (EmployeeInfoService)wac.getBean("employeeInfoService");
        
        patientService = (PatientService)wac.getBean("patientService");
        
        synYzByHisService = (SynYzByHisService)wac.getBean("synYzByHisService");
        
        synYdRecordByHisService = (SynYdRecordByHisService)wac.getBean("synYdRecordByHisService");

        inpatientAreaByHisService = (InpatientAreaByHisService)wac.getBean("inpatientAreaByHisService");
        
        medicalFrequencyByHisService = (MedicalFrequencyByHisService)wac.getBean("medicalFrequencyByHisService");
        
        drugWayByHisService = (DrugWayByHisService)wac.getBean("drugWayByHisService");
        
        medicamentsByHisService = (MedicamentsByHisService)wac.getBean("medicamentsByHisService");
        
        employeeInfoByHisService = (EmployeeInfoByHisService)wac.getBean("employeeInfoByHisService");
        
        patientByHisService = (PatientByHisService)wac.getBean("patientByHisService");
        
        
        // 这里从数据库中获取任务信息数据  
        jobList = scheduleJobDao.getAllTask();
        
        Calendar calendar = Calendar.getInstance();

        for (ScheduleJob job : jobList)
        {
            //只处理启用的任务的任务
            if (job.getJobEnable().equals(ScheduleJob.TASK_ENABLE))
            {
                try
                {
                    //服务器重启时，任务立即执行一次，执行时间是系统时间推迟20秒
                    Date date = new Date(System.currentTimeMillis());
                    calendar.setTime(date);
                    calendar.add(Calendar.SECOND, 10);
                    date = calendar.getTime();

                    job.setStartTime(date);
                    taskManagerService.createJob(job);
                }
                catch (SchedulerException e)
                {
                    log.error("create a schedule task failed, task name is" + job.getJobName());
                }
            }
        }
        
        try
        {
            //新建一个内部同步数据的任务
            ScheduleJob interScheduleJob = new ScheduleJob();
            interScheduleJob.setJobName(DemConstant.actionTypeDesc.interSyncTask);
            interScheduleJob.setAction(DemConstant.actionType.interSyncTask);
            interScheduleJob.setJobType(DemConstant.taskType.schedule);
            interScheduleJob.setStartTime(new Date());
            interScheduleJob.setExecMode(DemConstant.execMode.minute);
            interScheduleJob.setRunInterval(DemConstant.interTaskInterval);
            interScheduleJob.setBeanClass("com.zc.dem.taskcategory.syncDataScheduleTask");
            
            try
            {
                taskManagerService.createJob(interScheduleJob);
            }
            catch (SchedulerException e)
            {
                log.error("create internal sync task failed, task name is" + interScheduleJob.getJobName());
            }

            if (!scheduler.isShutdown())
            {
                scheduler.start();
            }
        }
        catch (SchedulerException e)
        {
            log.error("start the scheduler failed in startupservlet.");
        }
    }
}

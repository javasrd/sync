package com.zc.dem.restful;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.schedulejob.entity.ScheduleJob;
import com.zc.dem.schedulejob.repository.ScheduleJobDao;
import com.zc.dem.schedulejob.service.TaskManagerService;
import com.zc.dem.taskcategory.drugAdministration;
import com.zc.dem.taskcategory.drugfrequency;
import com.zc.dem.taskcategory.drugway;
import com.zc.dem.taskcategory.employee;
import com.zc.dem.taskcategory.excuteRecord;
import com.zc.dem.taskcategory.inpatientArea;
import com.zc.dem.taskcategory.medicamentDict;
import com.zc.dem.taskcategory.patient;

@Component
@Path("schedule")
public class ScheduleTaskServer
{
    /**
     * 一次性任务
     */
    private final static String TAKSTYPE_ONCE = "once";
    
    /**
     * 任务执行模式:分钟
     */
    private final static String TAKSTYPE_MINUTE = "minute";
    
    /**
     * 任务类型，0,定时任务 1,一次性任务 
     */
    private final static int PIVAS_TASKTYPE_ONCE = 1;
    
    /**
     * 任务类型，0,定时任务 1,一次性任务 
     */
    private final static int PIVAS_TASKTYPE_REPEAT = 0;
    
    /**
     * 任务执行模式名称，仅选择定时任务时有效, 0,分钟 1,天 
     */
    private final static int PIVAS_TASKTYPE_MINUTE = 0;
    
    /**
     * 任务执行模式名称，仅选择定时任务时有效, 0,分钟 1,天
     */
    private final static int PIVAS_TASKTYPE_DAY = 1;
    
    /**
     * 0病人
     */
    private final static int ACTION_PATIENT = 0;
    
    /**
     * 1医嘱
     */
    private final static int ACTION_ADVICE = 1;
    
    /**
     * 2药品字典
     */
    private final static int ACTION_DRUG = 2;
    
    /**
     * 3病区信息
     */
    private final static int ACTION_INPATIENT_AREA = 3;
    
    /**
     * 4医嘱频次
     */
    private final static int ACTION_ORDER_FREQUENCY = 4;
    
    /**
     * 5用药途径
     */
    private final static int ACTION_DRUGWAY = 5;
    
    /**
     * 6药单执行记录
     */
    private final static int EXCUTE_RECORD = 6;
    
    /**
     * 7员工
     */
    private final static int EXCUTE_EMPLOYEE = 7;
    
    @Resource
    private TaskManagerService taskManagerService;
    
    @Resource
    private ScheduleJobDao scheduleJobDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String taskCreate(String strParams) throws JSONException, NumberFormatException, ParseException
    {
        ScheduleJob schedulejob = exchangeJsonStr(strParams, true);
        
        Map<String, String> map = taskManagerService.addJob(schedulejob);
        return exchangeResult(map);
    }

    private String exchangeResult(Map<String, String> map)
        throws JSONException
    {
        JSONObject result = new JSONObject();
        
        result.put("result", map.get(DemConstant.response.retCode));
        result.put("resultMsg", map.get(DemConstant.response.retMsg));
        result.put("scheduleId", map.get(DemConstant.response.scheduleId));
        
        return result.toString();
    }

    private ScheduleJob exchangeJsonStr(String strParams, boolean isAdd)
        throws JSONException,NumberFormatException, ParseException
    {
        ScheduleJob schedulejob = new ScheduleJob();
        // 转义json字符串
        JSONObject request = new JSONObject(strParams);
        JSONObject execModeJson = new JSONObject(request.getString("execMode"));
        JSONObject scheduleJson = new JSONObject(request.getString("schedule"));
        schedulejob.setJobName(request.getString("name"));
        
        String cycleType = request.getString("cycleType");
        
        // 任务类型，0,定时任务 1,一次性任务 
        schedulejob.setJobType(PIVAS_TASKTYPE_REPEAT);
        if(TAKSTYPE_ONCE.equals(cycleType))
        {
            schedulejob.setJobType(PIVAS_TASKTYPE_ONCE);
        }
        else if (TAKSTYPE_MINUTE.equals(cycleType))
        {
            // 任务执行模式名称，仅选择定时任务时有效, 0,分钟 1,天 
            schedulejob.setExecMode(PIVAS_TASKTYPE_MINUTE);
            schedulejob.setRunInterval(Integer.parseInt(scheduleJson.getString("interval")));
        }
        else
        {
            schedulejob.setExecMode(PIVAS_TASKTYPE_DAY);
            schedulejob.setRunInterval(Integer.parseInt(scheduleJson.getString("interval")));
        }
        
        // 优先级
        schedulejob.setTaskPriority(Integer.parseInt(execModeJson.getString("taskPriority")));
        schedulejob.setRetryTime(Integer.parseInt(execModeJson.getString("retryTimes")));
        schedulejob.setRetryInterval(Integer.parseInt(execModeJson.getString("retryInterval")));
        
        // 执行时间
        String startTIme = scheduleJson.getString("taskStartTime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        schedulejob.setStartTime(format.parse(startTIme));
        if(isAdd)
        {
            schedulejob.setCreateTime(new Date());
        }
        
        schedulejob.setAction(request.getInt("action"));
        schedulejob.setActionBefore(request.getInt("action"));
        // 获取
        ScheduleJob schedulejobOld = scheduleJobDao.selectOneTask(schedulejob);
        if(null != schedulejobOld)
        {
            schedulejob.setActionBefore(schedulejobOld.getAction());
        }
        schedulejob.setJobEnable(Integer.parseInt(request.getString("enable")));
        schedulejob.setBeanClass(setBeanClass(request.getInt("action")));
        schedulejob.setUpdateTime(new Date());
        return schedulejob;
    }
    
    public String setBeanClass(int contentType)
    {
        String beanClass = "";
        
        switch (contentType)
        {
            case ACTION_PATIENT:
                beanClass = patient.class.getName();
                break;
            
            case ACTION_ADVICE:
                beanClass = drugAdministration.class.getName();
                break;
                
            case ACTION_DRUG:
                beanClass = medicamentDict.class.getName();
                break;
                
            case ACTION_INPATIENT_AREA:
                beanClass = inpatientArea.class.getName();
                break;
                
            case ACTION_ORDER_FREQUENCY:
                beanClass = drugfrequency.class.getName();
                break;
                
            case ACTION_DRUGWAY:
                beanClass = drugway.class.getName();
                break;
                
            case EXCUTE_RECORD:
                beanClass = excuteRecord.class.getName();
                break;
            case EXCUTE_EMPLOYEE:
                beanClass = employee.class.getName();
                break;
            default:
                break;
        }
        
        return beanClass;
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{scheduleId}")
    public String taskDelete(@PathParam("scheduleId") String scheduleId) throws JSONException
    {
        ScheduleJob schedulejob = new ScheduleJob();
        schedulejob.setJobId(Long.parseLong(scheduleId.split("=")[1]));
        Map<String, String> map = taskManagerService.deleteJob(schedulejob);

        return exchangeResult(map);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{scheduleId}/start")
    public String taskStart(@PathParam("scheduleId") String scheduleId) throws JSONException
    {
        ScheduleJob schedulejob = new ScheduleJob();
        schedulejob.setJobId(Long.parseLong(scheduleId.split("=")[1]));
        Map<String, String> map = taskManagerService.startJob(schedulejob);

        return exchangeResult(map);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{scheduleId}/modify")
    public String taskModify(@PathParam("scheduleId") String scheduleId, String strParams) throws NumberFormatException, JSONException, ParseException
    {
        ScheduleJob schedulejob = exchangeJsonStr(strParams,false);
        schedulejob.setJobId(Long.parseLong(scheduleId.split("=")[1]));
        Map<String, String> map = taskManagerService.modifyJob(schedulejob);

        return exchangeResult(map);
    }
}

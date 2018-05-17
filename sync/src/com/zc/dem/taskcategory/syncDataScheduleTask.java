package com.zc.dem.taskcategory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zc.dem.ActiveMQ.SyncData;
import com.zc.dem.common.StartUpServlet;
import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.synmessage.JaxbBinder;
import com.zc.dem.common.synmessage.analyresponse.msg.yz.YzRow;
import com.zc.dem.common.synmessage.request.ESBEntry.Query;
import com.zc.dem.common.util.FileUtil;
import com.zc.dem.common.util.Propertiesconfiguration;
import com.zc.dem.common.util.SetMessageForSynSZ;
import com.zc.dem.syndata.yz.bean.SynYzBean;

/**
 * 病人任务具体的执行
 * @author Joehart
 * @version 1.0
 */
@DisallowConcurrentExecution
public class syncDataScheduleTask implements Job
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(syncDataScheduleTask.class);
    
    /**
     * 同步模式：0ETL 1HIS 2VIEW
     */
    private static final String SYNCHRONIZATION_MODE_VIEW = "2";
    
    // 同步模式
    private static final String synchronizationMode =
        Propertiesconfiguration.getStringProperty("synhis.synchronizationmode");
    
    @Override
    public void execute(JobExecutionContext context)
        throws JobExecutionException
    {
        log.info("syncDataScheduleTask execute");
        //获取重试次数
//        ScheduleJob scheduleJob = (ScheduleJob)context.getJobDetail().getJobDataMap().get("schedulejob");
        
        //每次执行都记录执行结果到数据库中
//        ScheduleResult scheduleResult = new ScheduleResult();
//        scheduleResult.setTaskID(scheduleJob.getJobId());
//        scheduleResult.setTaskName(scheduleJob.getJobName());
//        scheduleResult.setTaskType(scheduleJob.getJobType());
//        scheduleResult.setTaskContent(scheduleJob.getAction());
//        scheduleResult.setTaskExecStartTime(new Date());
        
        // 病人
        Thread sendPatient = new Thread(new Runnable()
        {
            public void run()
            {
                sendPatient();
            }
        });
        sendPatient.start();
        
        // 医嘱
        Thread sendYZ = new Thread(new Runnable()
        {
            public void run()
            {
                sendYZ();
            }
        });
        sendYZ.start();
        
        // 药品字典
        Thread sendMedicamentDictMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendMedicamentDictMessage();
            }
        });
        sendMedicamentDictMessage.start();
        
        // 病区信息
        Thread sendInpatientAreaMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendInpatientAreaMessage();
            }
        });
        sendInpatientAreaMessage.start();
        
        // 员工信息
        Thread sendEmployeeInfoMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendEmployeeInfoMessage();
            }
        });
        sendEmployeeInfoMessage.start();
        
        // 药单执行记录
        Thread sendYdExcuteRecordMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendYdExcuteRecordMessage();
            }
        });
        sendYdExcuteRecordMessage.start();
        
        if (!SYNCHRONIZATION_MODE_VIEW.equals(synchronizationMode))
        {
            // 医嘱数据校验（针对his删除数据）
            Thread sendSyncYZDataDeleteScheduleTaskMessage = new Thread(new Runnable()
            {
                public void run()
                {
                    log.info("反向对比医嘱数据启动！" + new Date());
                    sendSyncYZDataDeleteScheduleTaskMessage();
                }
            });
            sendSyncYZDataDeleteScheduleTaskMessage.start();
        }
        
        // 医嘱频次
        Thread sendDrugfrequencyMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendDrugfrequencyMessage();
            }
        });
        sendDrugfrequencyMessage.start();
        
        // 用药途径
        Thread sendDrugAdministrationMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendDrugAdministrationMessage();
            }
        });
        sendDrugAdministrationMessage.start();
        
        // 同步结果
        Thread sendSyncDataScheduleTaskMessage = new Thread(new Runnable()
        {
            public void run()
            {
                sendSyncDataScheduleTaskMessage();
            }
        });
        sendSyncDataScheduleTaskMessage.start();
        
//        scheduleResult.setTaskExecStopTime(new Date());
//        scheduleResult.setTaskResult(DemConstant.operateResult.suc);
//        scheduleResult.setTaskID((long)99999);
//        StartUpServlet.scheduleJobDao.insertRecord(scheduleResult);
    }
    
    // 医嘱数据校验（针对his删除数据）
    private void sendSyncYZDataDeleteScheduleTaskMessage()
    {
        // 获取当前医嘱表中数据（执行状态，医嘱停止时间为空）
        List<SynYzBean> synYzList = StartUpServlet.synYzService.queryYZForHisDel();
        
        // 如果不为空，遍历数据，去his查询是否存在，不存在则修改为停止状态伟
        if (null != synYzList)
        {
            String condition = "";
            List<String> msgList = null;
            for (SynYzBean synYzBean : synYzList)
            {
                condition =
                    "Order_Group_No = " + synYzBean.getOrderGroupNo() + " AND ORDER_NO = " + synYzBean.getOrderNo();
                
                // 根据组id号查询当前医嘱状态
                String synRequest =
                    SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.actionAdvice, condition, new Query());
                
                // 调用接口
                try
                {
                    String synRespon = "";
                    
                    // 调用接口
                    if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
                    {
                        synRespon =
                            FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                                + "respon\\test.txt");
                    }
                    else
                    {
                        synRespon =
                            SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.actionAdvice, synRequest);
                    }
                    
                    msgList = SetMessageForSynSZ.analySynYZRespon(synRespon);
                    
                    // 如果为空，则说明此数据在his已删除，将当前状态修改为停止状态
                    if (null == msgList)
                    {
                        synYzBean.setOrderExecuteStatus(1);
                        StartUpServlet.synYzService.updateYzByCondition(synYzBean);
                    }
                    else
                    {
                        JaxbBinder jaxbBinder = new JaxbBinder(YzRow.class);
                        List<YzRow> msgDetailList = new ArrayList<YzRow>();
                        YzRow msgDetail = null;
                        for (String msg : msgList)
                        {
                            msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                            
                            msgDetailList.add(msgDetail);
                        }
                        
                        if (null != msgDetailList)
                        {
                            SynYzBean synYz = null;
                            List<SynYzBean> list = null;
                            for (YzRow row : msgDetailList)
                            {
                                if(!"0".equals(row.getOrder_Execute_Status()))
                                {
                                    list = new ArrayList<SynYzBean>();
                                    synYz = exchangeYzBean(row);
                                    list.add(synYz);
                                }
                            }
                            StartUpServlet.synYzService.synData(list);
                        }
                    }
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
    }
    
    /**
     * 医嘱数据转化
     * <一句话功能简述>
     * <功能详细描述>
     * @param row
     * @return
     * @throws ParseException 
     * @see [类、类#方法、类#成员]
     */
    private SynYzBean exchangeYzBean(YzRow row)
    {
        SynYzBean synYz = new SynYzBean();
        
        synYz.setAge(row.getAge());
        
        String ageUnit = row.getAge_Unit();
        
        if ("D".equals(ageUnit))
        {
            synYz.setAgeUnit(0);
        }
        else if ("M".equals(ageUnit))
        {
            synYz.setAgeUnit(1);
        }
        else
        {
            synYz.setAgeUnit(2);
        }
        synYz.setBedNo(row.getBed_No());
        
        // 出生日期格式化
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHH");
        try
        {
            synYz.setBirth(new Timestamp(sf1.parse(row.getDate_Birth()).getTime()));
        }
        catch (ParseException e1)
        {
            synYz.setBirth(new Timestamp(new Date().getTime()));
        }
        
        synYz.setDoseWayCode(row.getDose_Way_Code());
        synYz.setDropSpeed((row.getDrop_Speed() == null) ? "" : row.getDrop_Speed().trim());
        
        String drugAmount = row.getDrug_Amount();
        synYz.setDrugAmount(row.getDrug_Amount());
        if (drugAmount.startsWith("."))
        {
            synYz.setDrugAmount("0" + row.getDrug_Amount());
        }
        
        synYz.setDrugCode(row.getDrug_Code());
        synYz.setDrugName(row.getDrug_Name());
        synYz.setDrugPlaceCode(row.getDrug_Place_Code());
        
        synYz.setDrugUseOneDosAge(row.getDrug_Use_One_Dosage());
        if (row.getDrug_Use_One_Dosage().startsWith("."))
        {
            synYz.setDrugUseOneDosAge("0" + row.getDrug_Use_One_Dosage());
        }
        
        synYz.setDrugUseOneDosAgeUnit(row.getDrug_Use_One_Dosage_Unit());
        synYz.setInhospIndexNo(row.getInhosp_Index_No());
        synYz.setInhospNo(row.getInhosp_No());
        synYz.setNote(row.getNote());
        
        try
        {
            synYz.setNutritionliquidFlag(Integer.parseInt(row.getNutrition_Liquid_Flag()));
        }
        catch (NumberFormatException e)
        {
            synYz.setNutritionliquidFlag(0);
        }
        
        try
        {
            synYz.setYzlx(Integer.parseInt(row.getOrder_Categ_Code()));
        }
        catch (NumberFormatException e)
        {
            synYz.setYzlx(0);
        }
        
        synYz.setOrderFrequencyCode(row.getOrder_Frequency_Code());
        synYz.setOrderGroupNo(row.getOrder_Group_No());
        synYz.setOrderNo(row.getOrder_No());
        synYz.setOrderOpenDeptCode(row.getOrder_Open_Dept_Code());
        synYz.setOrderOpenDeptName(row.getOrder_Open_Dept_Name());
        synYz.setOrderOpendrCode(row.getOrder_Open_DR_Code());
        synYz.setOrderOpendrName(row.getOrder_Open_DR_Name());
        
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        
        try
        {
            synYz.setOrderOrderDate(new Timestamp(sf2.parse(row.getOrder_Order_Date()).getTime()));
        }
        catch (ParseException e1)
        {
            synYz.setOrderOrderDate(new Timestamp(new Date().getTime()));
        }
        try
        {
            synYz.setOrderStopDate(new Timestamp(sf2.parse(row.getOrder_Stop_Date().trim()).getTime()));
        }
        catch (ParseException e1)
        {
            synYz.setOrderStopDate(null);
        }
        
        String orderExecuteStatus = row.getOrder_Execute_Status();
        
        // 医嘱状态为执行，且医嘱停止时间不为空，且当前系统时间大于等于停止时间，返回停止结果
        SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd");
        if (0 == synYz.getYzlx() && "0".equals(orderExecuteStatus) && null != synYz.getOrderStopDate())
        {
            try
            {
                Date orderStopDate = fromat.parse(fromat.format(synYz.getOrderStopDate()));
                Date now = fromat.parse(fromat.format(new Date()));
                
                if (!orderStopDate.after(now))
                {
                    orderExecuteStatus = "1";
                }
            }
            catch (ParseException e)
            {
                log.error(e.getMessage(), e);
            }
            
        }
        else if ("2".equals(orderExecuteStatus))
        {
            orderExecuteStatus = "1";
        }
        
        // 短嘱
        if (1 == synYz.getYzlx() && "0".equals(orderExecuteStatus))
        {
            SimpleDateFormat sf3 = new SimpleDateFormat("yyyyMMdd");
            
            try
            {
                if (!sf3.format(sf2.parse(row.getOrder_Order_Date())).equals(sf3.format(new Date())))
                {
                    orderExecuteStatus = "1";
                }
            }
            catch (ParseException e)
            {
                orderExecuteStatus = "0";
            }
        }
        
        try
        {
            synYz.setOrderExecuteStatus(Integer.parseInt(orderExecuteStatus));
        }
        catch (NumberFormatException e)
        {
            synYz.setOrderExecuteStatus(0);
        }
        
        synYz.setPatName((row.getPat_Name() == null) ? "" : row.getPat_Name());
        synYz.setSex((row.getPhysic_Sex_Code() == null) ? "" : row.getPhysic_Sex_Code());
        
        String sexCode = synYz.getSex();
        
        if (!"".equals(sexCode))
        {
            synYz.setSex(("1".equals(sexCode)) ? "1" : "0");
        }
        
        synYz.setRecordDrCode(row.getRecord_DR_Code());
        synYz.setRecordDrName(row.getRecord_DR_Name());
        
        try
        {
            synYz.setSelfDrugFlag(Integer.parseInt(row.getSelf_Drug_Flag()));
        }
        catch (NumberFormatException e)
        {
            synYz.setSelfDrugFlag(0);
        }
        
        synYz.setSpecifications(row.getSpecifications());
        synYz.setAvdp(row.getWeight());
        
        // 同步时间
        SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        synYz.setSynData(sf3.format(new Date()));
        // 医嘱确认时间
        synYz.setConfirmDate("");
        if (null != row.getConfirm_date())
        {
            synYz.setConfirmDate(row.getConfirm_date().trim());
            
        }
        
        // 医嘱首日用药次数
        synYz.setFirstUseCount((row.getFirstUseCount() == null) ? "" : row.getFirstUseCount().trim());
        
        // 医嘱护士审核时间
        synYz.setCheckDate((row.getCheck_date() == null) ? "" : row.getCheck_date().trim());
        
        return synYz;
    }
    
    private void sendSyncDataScheduleTaskMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            
            JSONObject datas = StartUpServlet.setPushToMQMessage.setTaskResult();
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendSyncDataScheduleTaskMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send TaskResult request faile", e);
        }
    }
    
    private void sendDrugAdministrationMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            
            JSONObject datas = StartUpServlet.setPushToMQMessage.setDrugWay();
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendDrugAdministrationMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send DrugWay request faile", e);
        }
    }
    
    private void sendDrugfrequencyMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            JSONObject datas = StartUpServlet.setPushToMQMessage.setMedicalFrequency();
            
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendDrugfrequencyMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send MedicalFrequency request faile", e);
        }
    }
    
    private void sendInpatientAreaMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            JSONObject datas = StartUpServlet.setPushToMQMessage.setInPatientArea();
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendInpatientAreaMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send InPatientArea request faile", e);
        }
    }
    
    private void sendEmployeeInfoMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            JSONObject datas = StartUpServlet.setPushToMQMessage.setEmployeeInfo();
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendEmployeeInfoMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send EmployeeInfo request faile", e);
        }
    }
    
    private void sendMedicamentDictMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            JSONObject datas = StartUpServlet.setPushToMQMessage.setDrug();
            
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendMedicamentDictMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send Drug request faile", e);
        }
    }
    
    private void sendYZ()
    {
        try
        {
            SyncData syncData = new SyncData();
            
            JSONObject datas = StartUpServlet.setPushToMQMessage.setSynYz();
            
            if(datas.has("datas"))
            {
                JSONArray dataArray = datas.getJSONArray("datas");
                
                if (null != dataArray && dataArray.length() > 0)
                {
                    syncData.setData(datas.toString());
                    //根据队列不同调用不同的发送函数
                    StartUpServlet.queueProducer.sendDrugwayMessage(syncData);
                }
            }
            
            
        }
        catch (JSONException e)
        {
            log.error("send YZ request faile", e);
        }
    }
    
    private void sendPatient()
    {
        SyncData syncData = new SyncData();
        // 病人
        try
        {
            JSONObject datas = StartUpServlet.setPushToMQMessage.setPatient();
            
            JSONArray dataArray = datas.getJSONArray("datas");
            
            if (null != dataArray && dataArray.length() > 0)
            {
                syncData.setData(datas.toString());
                //根据队列不同调用不同的发送函数
                StartUpServlet.queueProducer.sendPatientMessage(syncData);
            }
            
        }
        catch (JSONException e)
        {
            log.error("send patient request faile", e);
        }
    }
    
    
    private void sendYdExcuteRecordMessage()
    {
        try
        {
            SyncData syncData = new SyncData();
            JSONObject datas = StartUpServlet.setPushToMQMessage.setYdExcuteRecord();
            
            if(datas.has("datas"))
            {
                JSONArray dataArray = datas.getJSONArray("datas");
                
                if (null != dataArray && dataArray.length() > 0)
                {
                    syncData.setData(datas.toString());
                    //根据队列不同调用不同的发送函数
                    StartUpServlet.queueProducer.sendYdExcuteRecordMessage(syncData);
                }
            }
            
            
        }
        catch (JSONException e)
        {
            log.error("send EmployeeInfo request faile", e);
        }
    }
}

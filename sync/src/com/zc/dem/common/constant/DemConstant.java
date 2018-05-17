package com.zc.dem.common.constant;

public interface DemConstant
{
    /*
     * 内部同步任务时间间隔单位:分钟
     */
    int interTaskInterval = 1;
    
    /*
     * 任务种类
     */
    interface actionType
    {
        int patient = 0;//病人
        
        int drugAdministration = 1;//医嘱
        
        int medicamentDict = 2;//药品字典
        
        int inpatientArea = 3;//病区信息
        
        int drugfrequency = 4; //医嘱频次 
        
        int drugway = 5;//用药途径
        
        int excuteRecord = 6;//药单执行记录
        
        int employee = 7;//员工
        
        int interSyncTask = 99; //内部同步到activeMQ任务
    }
    
    /*
     * 任务属性
     */
    interface taskType
    {
        int schedule = 0;//定时任务
        
        int once = 0;//一次性任务        
    }
    
    /*
     * 任务种类描述
     */
    interface actionTypeDesc
    {
        String patient = "patient";//病人
        
        String drugway = "drugway";//用药途径
        
        String medicamentDict = "medicamentDict";//药品字典
        
        String inpatientArea = "inpatientArea";//病区信息
        
        String drugfrequency = "drugfrequency"; //医嘱频次 
        
        String drugAdministration = "drugAdministration";//医嘱
        
        String excuteRecord = "excuteRecord";//药单执行记录
        
        String employee = "employee";//员工
        
        String interSyncTask = "interSyncTask";
    }
    
    /*
     * 操作返回
     */
    interface response
    {
        String retCode = "retCode"; //操作结果
        
        String retMsg = "retMsg"; //提示信息
        
        String scheduleId = "scheduleId"; //任务ID
    }
    
    /*
     * 操作返回取值
     */
    interface operateResult
    {
        int suc = 0; //成功
        
        int fasle = 1; //失败
    }
    
    /*
     * 错误定义
     */
    interface responseMsgDefine
    {
        String operSuc = "Operate successful!";
            
        String invalidParam = "Invalid parameter!";
        
        String taskNameDuplicated = "A same name task existed!";
        
        String insertOneTaskFailed = "Insert a new task to DB failed!";
        
        String createScheduleFailed = "quartz eror: create a schedule task failed!";
        
        String parseExpressionFailed = "quartz eror：parse schedule expression failed!";

    }
    
    interface osName
    {
        String windows = "windows";
        
        String linux = "linux";
    }
    
    interface execMode
    {
        int minute = 0;
        
        int day = 1;
    }
    
    interface dataFormat
    {
        String dataFormatStr = "yyyy-MM-dd HH:mm:ss";
    }
    
    /**
     * 某医院数据同步
     */
    interface synSZDataCode
    {
        // 药品字典
        String drug = "MS23002";
        
        // 病区
        String inPatientArea = "MS23003";
        
        // 员工
        String employeeInfo = "MS23004";
        
        // 医嘱频次
        String orderFrequency = "MS23005";
        
        // 用药途径
        String drugWay = "MS23006";
        
        // 病人
        String patient = "BS23001";
        
        // 医嘱
        String actionAdvice = "BS23002";
        
    }
    
    interface synDataType
    {
        /**
         * 0病人
         */
        int ACTION_PATIENT = 0;
        
        /**
         * 1医嘱
         */
        int ACTION_ADVICE = 1;
        
        /**
         * 2药品字典
         */
        int ACTION_DRUG = 2;
        
        /**
         * 3病区信息
         */
        int ACTION_INPATIENT_AREA = 3;
        
        /**
         * 4医嘱频次
         */
        int ACTION_ORDER_FREQUENCY = 4;
        
        /**
         * 5用药途径
         */
        int ACTION_DRUGWAY = 5;
        
        /**
         * 6药单执行记录
         */
        int ACTION_EXCUTERECORD = 6;
        
        /**
         * 7员工
         */
        int ACTION_EMPLOYEE = 7;
    }
}

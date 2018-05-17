package com.zc.dem.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.dem.syndata.drugway.bean.DrugWayBean;
import com.zc.dem.syndata.drugway.service.DrugWayService;
import com.zc.dem.syndata.employee.bean.EmployeeInfoBean;
import com.zc.dem.syndata.employee.service.EmployeeInfoService;
import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;
import com.zc.dem.syndata.excuteRecord.service.SynYdRecordService;
import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;
import com.zc.dem.syndata.inpatientarea.service.InpatientAreaService;
import com.zc.dem.syndata.medicalfrequency.entity.MedicalFrequency;
import com.zc.dem.syndata.medicalfrequency.service.MedicalFrequencyService;
import com.zc.dem.syndata.medicaments.entity.Medicaments;
import com.zc.dem.syndata.medicaments.service.MedicamentsService;
import com.zc.dem.syndata.patient.bean.PatientBean;
import com.zc.dem.syndata.patient.service.PatientService;
import com.zc.dem.syndata.taskresult.bean.TaskResultBean;
import com.zc.dem.syndata.taskresult.service.TaskResultService;
import com.zc.dem.syndata.yz.bean.SynYzBean;
import com.zc.dem.syndata.yz.service.SynYzService;
import com.zc.synhis.service.SynYzByHisService;

/**
 * 拼接对接mq的消息
 * @author Joehart
 * @version 1.0
 */
@Service("setPushToMQMessage")
public class SetPushToMQMessage
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(SetPushToMQMessage.class);
    
    /**
     * 同步模式：0ETL
     */
    private static final String SYNCHRONIZATION_MODE_ETL = "0";
    
    /**
     * 同步模式：0ETL 1HIS 2VIEW
     */
    private static final String SYNCHRONIZATION_MODE_VIEW = "2";
    
    // 同步模式
    private static final String synchronizationMode =
        Propertiesconfiguration.getStringProperty("synhis.synchronizationmode");
    
    /**
     * 病人数据操作
     */
    @Resource
    private PatientService patientService;
    
    /**
     * 病区
     */
    @Resource
    private InpatientAreaService inpatientAreaService;
    
    /**
     * 用药途径
     */
    @Resource
    private DrugWayService drugWayService;
    
    /**
     * 用药频次
     */
    @Resource
    private MedicalFrequencyService medicalFrequencyService;
    
    /**
     * 药品
     */
    @Resource
    private MedicamentsService medicamentsService;
    
    /**
     * 同步结果
     */
    @Resource
    private TaskResultService taskResultService;
    
    /**
     * 医嘱
     */
    @Resource
    private SynYzService synYzService;
    
    /**
     * 员工信息
     */
    @Resource
    private EmployeeInfoService employeeInfoService;
    
    /**
     * 药单执行记录
     */
    @Resource
    private SynYdRecordService synYdRecordService;
    
    @Resource
    private SynYzByHisService synYzByHisService; 
    
    public JSONObject setSynYz()
        throws JSONException
    {
        List<SynYzBean> synYzList = synYzService.queryAll();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != synYzList && synYzList.size() > 0)
        {
            JSONObject data = null;
            SynYzBean bean = null;
            for (int i = 0; i < synYzList.size(); i++)
            {
                bean = synYzList.get(i);
                
                if (SYNCHRONIZATION_MODE_ETL.equals(synchronizationMode))
                {
                    
                }
                else
                {
                    data = new JSONObject();
                    
                    // 医嘱的唯一性标志
                    data.put("orderNo", bean.getOrderNo());
                    
                    // 成组医嘱的唯一性标志
                    data.put("orderGroupNo", bean.getOrderGroupNo());
                    
                    // 开立医嘱的科室在医疗机构的科室代码
                    data.put("orderOpenDeptCode", bean.getOrderOpenDeptCode());
                    
                    // 开立医嘱的科室在医疗机构的科室名称
                    data.put("orderOpenDeptName", bean.getOrderOpenDeptName());
                    
                    // 患者住院期间，所住床位对应的编号
                    data.put("bedNo", bean.getBedNo());
                    
                    // 住院流水号
                    data.put("inhospNo", bean.getInhospNo());
                    
                    // 患者住院期间，住院对应的索引号，每次就诊相同，类似于就诊卡号。。
                    data.put("inhospIndexNo", bean.getInhospIndexNo());
                    
                    // 医嘱开立医生工号
                    data.put("orderOpenDRCode", bean.getOrderOpendrCode());
                    
                    // 医嘱开立医生姓名
                    data.put("orderOpenDRName", bean.getOrderOpendrName());
                    
                    // 录入医生工号
                    data.put("recordDRCode", bean.getRecordDrCode());
                    
                    // 录入医生姓名
                    data.put("recordDRName", bean.getRecordDrName());
                    
                    // 医嘱频次代码,对应医嘱频次信息
                    data.put("orderFrequencyCode", bean.getOrderFrequencyCode());
                    
                    // 用药途径代码,对应用药途径
                    data.put("doseWayCode", bean.getDoseWayCode());
                    
                    // 药品代码,对应药品字典
                    data.put("drugCode", bean.getDrugCode());
                    
                    // 药品名称
                    data.put("drugName", bean.getDrugName());
                    
                    // 规格
                    data.put("specifications", bean.getSpecifications());
                    
                    // 药品使用次剂量
                    data.put("drugUseOneDosage", bean.getDrugUseOneDosAge());
                    
                    // 药品使用次剂量单位
                    data.put("drugUseOneDosageUnit", bean.getDrugUseOneDosAgeUnit());
                    
                    // 药品数量
                    data.put("drugAmount", bean.getDrugAmount());
                    
                    // 医嘱开立时间
                    data.put("orderOrderDate", bean.getOrderOrderDate());
                    
                    // 医嘱停止时间
                    data.put("orderStopDate", bean.getOrderStopDate());
                    
                    // 备注
                    data.put("note", bean.getNote());
                    
                    // 自备药标志,0是，1不是, 默认不是
                    data.put("selfDrugFlag", bean.getSelfDrugFlag());
                    
                    // 营养液标志,0是，1不是, 默认不是
                    data.put("nutritionLiquidFlag", bean.getNutritionliquidFlag());
                    
                    // 医嘱执行状态,0：执行 1：停止 2：撤销
                    data.put("orderExecuteStatus", bean.getOrderExecuteStatus());
                    
                    // 药品产地代码
                    data.put("drugPlaceCode", bean.getDrugPlaceCode());
                    
                    // 医嘱类型
                    data.put("yzlx", bean.getYzlx());
                    
                    // 0(新增)、1(变更)
                    data.put("action", bean.getAction());
                    
                    // 判断当前同步模式
                    if (SYNCHRONIZATION_MODE_ETL.equals(synchronizationMode))
                    {
                        // 执行日期
                        data.put("zxrq", bean.getZxrq());
                        
                        // 执行时间
                        data.put("zxsj", bean.getZxsj());
                        
                        // 执行时间
                        data.put("medicamentsPackingUnit", bean.getMedicamentsPackingUnit());
                    }
                    else
                    {
                        
                        // 患者姓名
                        data.put("patName", bean.getPatName());
                        
                        // 生理性别代码0:女 1：男
                        data.put("sex", bean.getSex());
                        
                        // 出生日期，HIS过来的格式是yyyymmdd
                        data.put("birth", bean.getBirth());
                        
                        // 年龄
                        data.put("age", bean.getAge());
                        
                        // 年龄单位，0代表天，1代表月，2代表年，HIS过来的是D（天）、M（月）、Y（年）
                        data.put("ageUnit", bean.getAgeUnit());
                        
                        // 病人体重kg
                        data.put("avdp", bean.getAvdp());
                        
                        // 医嘱滴速
                        data.put("dropSpeed", bean.getDropSpeed());
                        
                        // 医嘱确认时间
                        data.put("confirmDate", bean.getConfirmDate());
                        
                        // 医嘱护士审核时间
                        data.put("checkDate", bean.getCheckDate());
                        
                        // 医嘱首日用药次数
                        data.put("firstUseCount", bean.getFirstUseCount());
                        
                        // 同步时间
                        data.put("synData", bean.getSynData());
                    }
                    
                    dataArray.put(i, data);
                }
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        synYzService.deleteAll();
        return datas;
    }
    
    /**
     * 同步结果
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws JSONException
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setTaskResult()
        throws JSONException
    {
        List<TaskResultBean> taskResultList = taskResultService.queryAll();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != taskResultList && taskResultList.size() > 0)
        {
            JSONObject data = null;
            TaskResultBean bean = null;
            for (int i = 0; i < taskResultList.size(); i++)
            {
                bean = taskResultList.get(i);
                data = new JSONObject();
                
                // 定时任务唯一标识
                data.put("scheduleId", bean.getTaskID());
                
                // 任务名称
                data.put("name", bean.getTaskName());
                
                // 任务类型，0,定时任务 1,一次性任务
                data.put("TYPE", bean.getTaskType());
                
                // 执行结果
                data.put("result", bean.getTaskResult());
                
                // 执行开始时间
                data.put("taskExecStartTime", bean.getTaskExecStartTime());
                
                // 执行结束时间
                data.put("taskExecStopTime", bean.getTaskExecStopTime());
                
                // 任务执行内容类型 0,病人、1,医嘱、2,病区、3,用药方式、4,医嘱频次、5,药品
                data.put("CONTENT_TYPE", bean.getTaskContentType());
                
                dataArray.put(i, data);
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        taskResultService.deleteAll();
        return datas;
    }
    
    /**
     * 封装药品返回信息
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws JSONException
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setDrug()
        throws JSONException
    {
        List<Medicaments> medicalist = medicamentsService.queryAll();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != medicalist && medicalist.size() > 0)
        {
            JSONObject data = null;
            Medicaments bean = null;
            for (int i = 0; i < medicalist.size(); i++)
            {
                bean = medicalist.get(i);
                data = new JSONObject();
                
                // 药品代码
                data.put("drugCode", bean.getMedicamentsCode());
                
                // 药品名称
                data.put("drugName", bean.getMedicamentsName());
                
                // 规格
                data.put("specifications", bean.getMedicamentsSpecifications());
                
                // 药品使用次剂量
                data.put("drugUseOneDosage", bean.getMedicamentsDosage());
                
                // 药品使用次剂量单位
                data.put("drugUseOneDosageUnit", bean.getMedicamentsDosageUnit());
                
                // 拼音码
                data.put("pinyinCode", bean.getMedicamentsSuchama());
                
                // 体积
                data.put("drugVolume", bean.getMedicamentsVolume());
                
                // 体积单位
                data.put("drugVolumeUnit", bean.getMedicamentsVolumeUnit());
                
                // 包装单位
                data.put("drugPackingUnit", bean.getMedicamentsPackingUnit());
                
                // 皮试标志,0需要，1不需要，默认不需要
                data.put("skinTestFlag", bean.getMedicamentsTestFlag());
                
                //高危药品标识0:高危，1非高危
                data.put("high_risk", bean.getMedicamentsDanger());
                
                //单价
                data.put("price", bean.getMedicamentsPrice());
                
                // 货架号
                data.put("shelfNo", bean.getShelfNo());
                
                // 药品产地
                data.put("drugPlace", bean.getMedicamentsPlace());
                
                // 药品产地编码
                data.put("drugPlaceCode", bean.getMedicamentsPlaceCode());
                
                // 药品分类ID
                data.put("Special_Drug", bean.getCategoryId());
                
                if (null != bean.getMedicamentsMenstruum())
                {
                    // 是否溶媒
                    data.put("solvent_flag", bean.getMedicamentsMenstruum());
                }
                
                if (null != bean.getMedicamentsIsmaindrug())
                {
                    // 是否主药
                    data.put("main_drug_flag", bean.getMedicamentsIsmaindrug());
                }
                
                // 有效期
                data.put("effective_date", bean.getEffective_date());
                
                // 难度系数
                data.put("difficulty_degree", bean.getDifficulty_degree());
                
                data.put("phyFunctiy", bean.getPhyFunctiy());
                
                // 0(新增)、1(变更)
                data.put("action", bean.getAction());
                
                dataArray.put(i, data);
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        medicamentsService.deleteAll();
        return datas;
        
    }
    
    /**
     * 封裝用藥频次
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws JSONException 
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setMedicalFrequency()
        throws JSONException
    {
        List<MedicalFrequency> medicalFrequencyList = medicalFrequencyService.queryAll();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != medicalFrequencyList && medicalFrequencyList.size() > 0)
        {
            JSONObject data = null;
            MedicalFrequency bean = null;
            for (int i = 0; i < medicalFrequencyList.size(); i++)
            {
                bean = medicalFrequencyList.get(i);
                data = new JSONObject();
                
                // 医嘱频次代码
                data.put("orderFrequencyCode", bean.getCode());
                
                // 医嘱频次名称
                data.put("orderFrequencyName", bean.getName());
                
                // 0(新增)、1(变更)
                data.put("action", bean.getAction());
                
                dataArray.put(i, data);
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        medicalFrequencyService.deleteAll();
        return datas;
        
    }
    
    /**
     * 封装用药途径信息
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws JSONException
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setDrugWay()
        throws JSONException
    {
        List<DrugWayBean> drugWayList = drugWayService.getDrugWayList();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != drugWayList && drugWayList.size() > 0)
        {
            JSONObject data = null;
            DrugWayBean bean = null;
            for (int i = 0; i < drugWayList.size(); i++)
            {
                bean = drugWayList.get(i);
                data = new JSONObject();
                
                // 病区编码
                data.put("ID", bean.getId());
                
                // 病区名称
                data.put("code", bean.getCode());
                
                // 病区名称
                data.put("name", bean.getName());
                
                // 0(新增)、1(变更)
                data.put("action", bean.getAction());
                
                dataArray.put(i, data);
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        drugWayService.delAllSynDrugWays();
        return datas;
        
    }
    
    /**
     * 封装病区信息
     * 
     * @return
     * @throws JSONException 
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setInPatientArea()
        throws JSONException
    {
        List<InpatientAreaBean> inpatientAreaList = inpatientAreaService.getInpatientAreaList();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != inpatientAreaList && inpatientAreaList.size() > 0)
        {
            JSONObject data = null;
            InpatientAreaBean bean = null;
            for (int i = 0; i < inpatientAreaList.size(); i++)
            {
                bean = inpatientAreaList.get(i);
                data = new JSONObject();
                
                // 病区编码
                data.put("deptCode", bean.getDeptCode());
                
                // 病区名称
                data.put("deptName", bean.getDeptName());
                
                // 0(新增)、1(变更)
                data.put("action", bean.getAction());
                
                dataArray.put(i, data);
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        inpatientAreaService.delAllSynInpatientAreas();
        return datas;
    }
    
    /**
     * 封装员工信息
     * 
     * @return
     * @throws JSONException 
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setEmployeeInfo()
        throws JSONException
    {
        List<EmployeeInfoBean> employeeInfoList = employeeInfoService.queryAll();
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != employeeInfoList && employeeInfoList.size() > 0)
        {
            JSONObject data = null;
            EmployeeInfoBean bean = null;
            for (int i = 0; i < employeeInfoList.size(); i++)
            {
                bean = employeeInfoList.get(i);
                data = new JSONObject();
                
                // 编码
                data.put("staff_Code", bean.getStaff_Code());
                
                // 名称
                data.put("staff_Name", bean.getStaff_Name());
                
                // 0(新增)、1(变更)
                data.put("action", bean.getAction());
                
                dataArray.put(i, data);
            }
        }
        
        datas.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        employeeInfoService.deleteAll();
        return datas;
    }
    
    /**
     * 封装病人响应信息
     * 
     * @return 病人数据
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setPatient()
        throws JSONException
    {
        List<PatientBean> patientList = patientService.getPatientSynList();
        JSONObject datasResult = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != patientList && patientList.size() > 0)
        {
            JSONObject data = null;
            PatientBean bean = null;
            for (int i = 0; i < patientList.size(); i++)
            {
                bean = patientList.get(i);
                data = new JSONObject();
                
                // 住院流水号，病人唯一编码标识
                data.put("inhospNo", bean.getInhospNo());
                
                // 病人住院号
                data.put("CASE_ID", bean.getCase_ID());
                
                // 患者姓名
                data.put("patName", bean.getPatName());
                
                // 性别：0女，1男
                data.put("sex", bean.getSex());
                
                // 病人出生日期
                data.put("BIRTHDAY", bean.getBirthDay());
                
                // 病人年龄
                data.put("AGE", bean.getAge());
                
                // 年龄单位，0天 1月 2年
                data.put("AGEUNIT", bean.getAgeUnit());
                
                // 病人体重
                data.put("AVDP", bean.getAvdp());
                
                // 病人当前病区(科室)。对应病区表
                data.put("wardCode", bean.getWardCode());
                
                // 患者住院期间，所住床位对应的编号
                data.put("bedNo", bean.getBedNo());
                
                // 病人当前状态。 如病人状态对HIS的医嘱状态或药单生成产生影响，此值必须提供。如HIS无此值，可为空。1是 0 不是,-1病人预出院
                data.put("state", bean.getState());
                
                // 病人预出院时间
                data.put("hosOutTime", bean.getHosOutTime());
                
                // 病人预出院状态
                //data.put("hosOutSta", bean.getHosOutSta());
                
                // 0(新增)、1(变更)
                data.put("action", bean.getAction());
                
                dataArray.put(i, data);
            }
        }
        
        datasResult.put("datas", dataArray);
        
        // 数据封装成功后，删除同步表中的数据
        patientService.delAllSynPatients();
        return datasResult;
    }
    
    /**
     * 封装员工信息
     * 
     * @return
     * @throws JSONException 
     * @see [类、类#方法、类#成员]
     */
    public JSONObject setYdExcuteRecord()
        throws JSONException
    {
        List<ExcuteRecordBean> list = synYdRecordService.queryAll();
        
        JSONObject datas = new JSONObject();
        JSONArray dataArray = new JSONArray();
        
        if (null != list && list.size() > 0)
        {
            JSONObject data = null;
            ExcuteRecordBean bean = null;
            String schedule = "";
            Integer ydRecordCount = null;
            
            Map<String, Object> conditon = null;
            
            Map<String, Object> yzCondition = null;
            
            List<SynYzBean> yzList = null;
            
            // 医嘱类型： 0 长 1 临
            int yzlx = 0;
            for (int i = 0; i < list.size(); i++)
            {
                yzlx = 0;
                try
                {
                    
                    bean = list.get(i);
                    
                    // 判断当前同步模式
                    if (SYNCHRONIZATION_MODE_ETL.equals(synchronizationMode))
                    {
                        
                    }
                    // 通过调用HIS数据库视图同步数据
                    else if (SYNCHRONIZATION_MODE_VIEW.equals(synchronizationMode))
                    {
                        if (StringUtils.isNotEmpty(bean.getOccDT()))
                        {
                            // 根据主医嘱号获取HIS医嘱类型
                            yzCondition = new HashMap<String, Object>();
                            yzCondition.put("order_group_no", bean.getGroupNo());
                            yzList = synYzService.qryYzList(yzCondition);

                            if(CollectionUtils.isEmpty(yzList))
                            {
                                yzList = synYzByHisService.qryYzList(yzCondition);
                            }
                            
                            if(!CollectionUtils.isEmpty(yzList) && yzList.get(0).getYzlx() == 1)
                            {
                                yzlx = 1;
                            }
                            
                            
                            // 根据用药输液时间匹配批次
                            conditon = new HashMap<String, Object>();
                            conditon.put("pivasUser", bean.getPivasUser());
                            conditon.put("useTime", bean.getOccDT().substring(11, 16));
                            
                            if(yzlx == 1 )
                            {
                                conditon.put("yzlx", yzlx);
                            }
                            
                            String batchID = null;
                            
                            try
                            {
                                batchID = synYdRecordService.getBatchID(conditon);
                            }
                            catch (Exception e)
                            {
                                log.error("获取批次id失败，药单编号：" +bean.getDrugListID()+ "查询条件为：" + conditon.toString());
                            }
                           
                            
                            if (StringUtils.isNotEmpty(batchID))
                            {
                                // 判断该批次号是否已被使用
                                bean.setBatchID(batchID);
                                String checkResutl = synYdRecordService.checkBatchUsed(bean);
                                
                                // 不为空，说明该批次已被使用
                                if (StringUtils.isNotEmpty(checkResutl))
                                {
                                    // 获取pivas中预制的批次id
                                    List<String> batchOtherList = synYdRecordService.getBathListOther(bean);
                                    bean.setBatchID(batchOtherList.get(0));
                                }
                            }
                            
                            if (StringUtils.isEmpty(bean.getSchedule()))
                            {
                                conditon = new HashMap<String, Object>();
                                conditon.put("pivasUser", bean.getPivasUser());
                                conditon.put("drugListID", bean.getDrugListID());
                                // 判断该药单号的序列号是否存在
                                schedule = synYdRecordService.getSchedule(conditon);
                                
                                if (StringUtils.isNotEmpty(schedule))
                                {
                                    bean.setSchedule(schedule);
                                }
                                else
                                {
                                    conditon = new HashMap<String, Object>();
                                    conditon.put("pivasUser", bean.getPivasUser());
                                    conditon.put("groupNo", bean.getGroupNo());
                                    conditon.put("useTime", bean.getOccDT().substring(0, 10));
                                    ydRecordCount = synYdRecordService.getYdRecordCount(conditon);
                                    
                                    bean.setSchedule(0 == ydRecordCount ? "1" : String.valueOf(ydRecordCount + 1));
                                }
                            }
                            
                            synYdRecordService.synDataPivas(bean);
                        }
                    }
                    else
                    {
                        data = new JSONObject();
                        
                        data.put("RecipeID", bean.getRecipeID());
                        data.put("GroupNo", bean.getGroupNo());
                        data.put("DrugListID", bean.getDrugListID());
                        data.put("DrugFreq", bean.getDrugFreq());
                        data.put("DrugCode", bean.getDrugCode());
                        data.put("DrugName", bean.getDrugName());
                        data.put("Quantity", bean.getQuantity());
                        data.put("QuantityUnit", bean.getQuantityUnit());
                        data.put("Schedule", bean.getSchedule());
                        data.put("OccDT", bean.getOccDT());
                        data.put("ChargeDT", bean.getChargeDT());
                        data.put("InfusionDate", bean.getInfusionDate());
                        data.put("LabelNo", bean.getLabelNo());
                        data.put("Begindt", bean.getBegindt());
                        data.put("Enddt", bean.getEnddt());
                        data.put("amount", bean.getAmount());
                        dataArray.put(i, data);
                    }
                    
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
            }
        }
        
        if (SYNCHRONIZATION_MODE_ETL.equals(synchronizationMode))
        {
            datas.put("datas", dataArray);
        }
        
        // 数据封装成功后，删除同步表中的数据
        synYdRecordService.deleteAll();
        return datas;
    }
    
    @Autowired
    public void setPatientService(PatientService patientService)
    {
        this.patientService = patientService;
    }
    
    @Autowired
    public void setInpatientAreaService(InpatientAreaService inpatientAreaService)
    {
        this.inpatientAreaService = inpatientAreaService;
    }
    
    @Autowired
    public void setDrugWayService(DrugWayService drugWayService)
    {
        this.drugWayService = drugWayService;
    }
    
    @Autowired
    public void setMedicalFrequencyService(MedicalFrequencyService medicalFrequencyService)
    {
        this.medicalFrequencyService = medicalFrequencyService;
    }
    
    @Autowired
    public void setMedicamentsService(MedicamentsService medicamentsService)
    {
        this.medicamentsService = medicamentsService;
    }
    
    @Autowired
    public void setTaskResultService(TaskResultService taskResultService)
    {
        this.taskResultService = taskResultService;
    }
    
    @Autowired
    public void setSynYzService(SynYzService synYzService)
    {
        this.synYzService = synYzService;
    }
    
}

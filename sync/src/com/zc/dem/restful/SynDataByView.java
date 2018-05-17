package com.zc.dem.restful;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.util.EncodeUtil;
import com.zc.dem.common.util.SetPushToMQMessage;
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
import com.zc.dem.syndata.yz.bean.SynYzBean;
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
 * ETL脚本同步数据
 * @author Joehart
 * @version 1.0
 */
@Service("synDataByView")
public class SynDataByView
{
    /**
     * 推送消息到mq
     */
    @Resource
    private SetPushToMQMessage setPushToMQMessage;
    
    /**
     * 病人(视图)
     */
    @Resource
    private PatientByHisService patientByHisService;
    
    /**
     * 医嘱(视图)
     */
    @Resource
    private SynYzByHisService synYzByHisService;
    
    /**
     * 药品(视图)
     */
    @Resource
    private MedicamentsByHisService medicamentsByHisService;
    
    /**
     * 病区(视图)
     */
    @Resource
    private InpatientAreaByHisService inpatientAreaByHisService;
    
    /**
     * 频次(视图)
     */
    @Resource
    private MedicalFrequencyByHisService medicalFrequencyByHisService;
    
    /**
     * 用药途径(视图)
     */
    @Resource
    private DrugWayByHisService drugWayByHisService;
    
    /**
     * 药单执行记录(视图)
     */
    @Resource
    private SynYdRecordByHisService synYdRecordByHisService;
    
    /**
     * 员工(视图)
     */
    @Resource
    private EmployeeInfoByHisService employeeInfoByHisService;
    
    /**
     * 病人
     */
    @Resource
    private PatientService patientService;
    
    /**
     * 医嘱
     */
    @Resource
    private SynYzService synYzService;
    
    /**
     * 药品
     */
    @Resource
    private MedicamentsService medicamentsService;
    
    /**
     * 病区
     */
    @Resource
    private InpatientAreaService inpatientAreaService;
    
    /**
     * 频次
     */
    @Resource
    private MedicalFrequencyService medicalFrequencyService;
    
    /**
     * 用药途径
     */
    @Resource
    private DrugWayService drugWayService;
    
    /**
     * 药单执行记录
     */
    @Resource
    private SynYdRecordService synYdRecordService;
    
    /**
     * 员工
     */
    @Resource
    private EmployeeInfoService employeeInfoService;
    
    
    /**
     * 视图同步数据模式
     * <一句话功能简述>
     * <功能详细描述>
     * @param resultMap
     * @param dataType
     * @return
     * @throws JSONException
     * @throws IOException 
     * @see [类、类#方法、类#成员]
     */
    public String synData(JSONObject data,Map<String, String> resultMap, Integer dataType)
        throws JSONException, IOException
    {  
        Map<String, Object> map = null;
        List<PatientBean> patientList = null;
        switch (dataType)
        {
            case DemConstant.synDataType.ACTION_PATIENT:
                patientList = patientByHisService.qryPatientList();
                
                if (patientList != null && patientList.size() > 0)
                {
                    patientService.synData(patientList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_ADVICE:
                List<SynYzBean> yzList = null;
                if (data.has("condition"))
                {
                    map = new HashMap<String, Object>();
                    String condition = data.getString("condition");
                    JSONObject con = new JSONObject(condition);
                    if(con.has("order_group_no"))
                    {
                        map.put("order_group_no", con.get("order_group_no"));  
                    }
                }   
                yzList = synYzByHisService.qryYzList(map);
                
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
                    synYzService.synData(yzList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_DRUG:
                List<Medicaments> medicamentList = medicamentsByHisService.qryMedicamentList();
                
                if (medicamentList != null && medicamentList.size() > 0)
                {
                    for (Medicaments bean : medicamentList)
                    {
                        if(StringUtils.isNotEmpty(bean.getMedicamentsPrice()) && bean.getMedicamentsPrice().startsWith("."))
                        {
                            bean.setMedicamentsPrice("0" + bean.getMedicamentsPrice());
                        }
                        if(StringUtils.isNotEmpty(bean.getMedicamentsDosage()) && bean.getMedicamentsDosage().startsWith("."))
                        {
                            bean.setMedicamentsDosage("0" + bean.getMedicamentsDosage());
                        }
                    }
                    medicamentsService.synData(medicamentList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_INPATIENT_AREA:
                List<InpatientAreaBean> inparentAreaList = inpatientAreaByHisService.qryInpatientAreaList();
                
                if (inparentAreaList != null && inparentAreaList.size() > 0)
                {
                     inpatientAreaService.synData(inparentAreaList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_ORDER_FREQUENCY:
                List<MedicalFrequency> medicalFrequencyList = medicalFrequencyByHisService.qryMedicalFrequencyList();
                
                if (medicalFrequencyList != null && medicalFrequencyList.size() > 0)
                {
                    medicalFrequencyService.synData(medicalFrequencyList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_DRUGWAY:
                List<DrugWayBean> drugWayList = drugWayByHisService.qryDrugWayList();
                
                if (drugWayList != null && drugWayList.size() > 0)
                {
                    drugWayService.synData(drugWayList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_EXCUTERECORD:
                List<ExcuteRecordBean> recordList = null;
                if (data.has("condition"))
                {
                    map = new HashMap<String, Object>();
                    String condition = data.getString("condition");
                    JSONObject con = new JSONObject(condition);
                    if(con.has("order_group_no"))
                    {
                        map.put("order_group_no", con.get("order_group_no"));  
                    }
                }   
                
                recordList = synYdRecordByHisService.qryYdRecordList(map);
                
                if (recordList != null && recordList.size() > 0)
                {
                    for (ExcuteRecordBean bean : recordList)
                    {
                        if(StringUtils.isNotEmpty(bean.getQuantity()) && bean.getQuantity().startsWith("."))
                        {
                            bean.setQuantity("0" + bean.getQuantity());
                        }
                    }
                    synYdRecordService.synData(recordList);
                }
                break;
            
            case DemConstant.synDataType.ACTION_EMPLOYEE:
                List<EmployeeInfoBean> employeeList = employeeInfoByHisService.qryEmployeeInfoList();
                
                if (employeeList != null && employeeList.size() > 0)
                {
                    employeeInfoService.synData(employeeList);
                }
                break;
            
            default:
                patientList = patientByHisService.qryPatientList();
                
                if (patientList != null && patientList.size() > 0)
                {
                    patientService.synData(patientList);
                }
                break;
        }
        return exchangeResult(resultMap);
    }
    
    /**
     * 封装响应消息
     * 
     * @param map
     * @return
     * @throws JSONException
     * @throws IOException 
     * @see [类、类#方法、类#成员]
     */
    private String exchangeResult(Map<String, String> resultMap)
        throws JSONException, IOException
    {
        JSONObject result = new JSONObject();
        
        JSONObject param = new JSONObject();
        
        result.put("result", resultMap.get("result"));
        
        // 判断同步数据类型
        if (null != resultMap.get("dataType") && !"".equals(resultMap.get("dataType")))
        {
            int dataType = Integer.parseInt(resultMap.get("dataType"));
            // 根据同步数据类型拼接返回数据
            switch (dataType)
            {
                case DemConstant.synDataType.ACTION_PATIENT:
                    param = setPushToMQMessage.setPatient();
                    break;
                
                case DemConstant.synDataType.ACTION_ADVICE:
                    param = setPushToMQMessage.setSynYz();
                    break;
                
                case DemConstant.synDataType.ACTION_DRUG:
                    param = setPushToMQMessage.setDrug();
                    break;
                
                case DemConstant.synDataType.ACTION_INPATIENT_AREA:
                    param = setPushToMQMessage.setInPatientArea();
                    break;
                
                case DemConstant.synDataType.ACTION_ORDER_FREQUENCY:
                    param = setPushToMQMessage.setMedicalFrequency();
                    break;
                
                case DemConstant.synDataType.ACTION_DRUGWAY:
                    param = setPushToMQMessage.setDrugWay();
                    break;
                    
                case DemConstant.synDataType.ACTION_EXCUTERECORD:
                    param = setPushToMQMessage.setYdExcuteRecord();
                    break;
                    
                case DemConstant.synDataType.ACTION_EMPLOYEE:
                    param = setPushToMQMessage.setEmployeeInfo();
                    break;
                
                default:
                    param = setPushToMQMessage.setPatient();
                    break;
            }
            
            result.put("param", EncodeUtil.encode(EncodeUtil.GZip(param.toString()).getBytes("iso8859-1")));
            
        }
        
        return result.toString();
    }
}

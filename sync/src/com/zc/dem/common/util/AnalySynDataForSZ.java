package com.zc.dem.common.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.synmessage.JaxbBinder;
import com.zc.dem.common.synmessage.analyresponse.msg.drug.DrugMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.drug.DrugRow;
import com.zc.dem.common.synmessage.analyresponse.msg.drugway.DrugWayMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.drugway.DrugWayRow;
import com.zc.dem.common.synmessage.analyresponse.msg.employeeinfo.EmployeeInfoMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.employeeinfo.EmployeeInfoRow;
import com.zc.dem.common.synmessage.analyresponse.msg.inpatientarea.InPatientAreaMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.inpatientarea.InPatientAreaRow;
import com.zc.dem.common.synmessage.analyresponse.msg.orderfrequency.OrderFrequencyMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.orderfrequency.OrderFrequencyRow;
import com.zc.dem.common.synmessage.analyresponse.msg.patient.PatientMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.patient.PatientRow;
import com.zc.dem.common.synmessage.analyresponse.msg.yz.YzRow;
import com.zc.dem.common.synmessage.analyresponse.msg.yzRecord.YzRecordRow;
import com.zc.dem.common.synmessage.request.ESBEntry.Query;
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

/**
 * 某医院返回数据处理
 * @author Joehart
 * @version 1.0
 */
@Service("analySynDataForSZ")
public class AnalySynDataForSZ
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(AnalySynDataForSZ.class);
    
    /**
     * 病人数据操作
     */
    @Resource
    private PatientService patientService;
    
    /**
     * 药品数据操作
     */
    @Resource
    private MedicamentsService medicamentsService;
    
    /**
     * 医嘱频次
     */
    @Resource
    private MedicalFrequencyService medicalFrequencyService;
    
    /**
     * 用药途径
     */
    @Resource
    private DrugWayService drugWayService;
    
    /**
     * 病区
     */
    @Resource
    private InpatientAreaService inpatientAreaService;
    
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
    
    /**
     * 病人
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgDetailList
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public void analyPatient(List<PatientMsg> msgDetailList)
        throws ParseException
    {
        PatientRow row = null;
        PatientBean patient = null;
        String ageUnit = "";
        
        List<PatientBean> patientList = new ArrayList<PatientBean>();
        // 出生日期格式化
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        String sexCode = "";
        for (PatientMsg patientMsg : msgDetailList)
        {
            row = patientMsg.getBody().getRow();
            
            // 同步数据转本地
            patient = new PatientBean();
            patient.setInhospNo(row.getInhosp_No());
            patient.setBedNo(row.getBed_No());
            patient.setPatName(row.getPat_Name());
            
            sexCode = ("1".equals(row.getPhysic_Sex_Code())) ? "1" : "0";
            patient.setSex(Integer.parseInt(sexCode));
            patient.setState(row.getState());
            patient.setWardCode(row.getWard_Code());
            patient.setAge((row.getAge() == null ? "" : row.getAge()));
            
            ageUnit = row.getAge_Unit().trim();
            
            // -1 表示传过来的年龄为中文
            patient.setAgeUnit(-1);
            
            if (!"".equals(ageUnit))
            {
                if ("D".equals(ageUnit))
                {
                    patient.setAgeUnit(0);
                }
                else if ("M".equals(ageUnit))
                {
                    patient.setAgeUnit(1);
                }
                else
                {
                    patient.setAgeUnit(2);
                }
            }
            
            try
            {
                patient.setBirthDay(new Timestamp(sf1.parse(row.getDate_Birth()).getTime()));
            }
            catch (Exception e)
            {
                patient.setBirthDay(null);
            }
            
            patient.setAvdp((row.getWeight() == null ? "" : row.getWeight()));
            
            patient.setCase_ID((row.getInhosp_Index_No() == null) ? "" : row.getInhosp_Index_No());
            
            // 病人预出院状态
            //patient.setHosOutSta(row.getHosOutSta());
            
            // 病人预出院时间
            patient.setHosOutTime(row.getHosOutTime().trim());
            patientList.add(patient);
        }
        patientService.synData(patientList);
    }
    
    /**
     * 药品
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgDetailList
     * @see [类、类#方法、类#成员]
     */
    public void analyDrug(List<DrugMsg> msgDetailList)
    {
        DrugRow row = null;
        
        Medicaments medicaments = null;
        
        List<Medicaments> list = new ArrayList<Medicaments>();
        // 药品使用次剂量
        String drug_Use_One_Dosage = "";
        
        for (DrugMsg drugMsg : msgDetailList)
        {
            row = drugMsg.getBody().getRow();
            medicaments = new Medicaments();
            medicaments.setMedicamentsCode(row.getDrug_Code() + "_" + row.getDrug_Place_Code());
            medicaments.setMedicamentsName(row.getDrug_Name());
            medicaments.setMedicamentsPackingUnit(row.getDrug_Packing_Unit());
            medicaments.setMedicamentsPlace(row.getDrug_Place() == null ? "" : row.getDrug_Place().trim());
            medicaments.setMedicamentsPlaceCode(row.getDrug_Place_Code() == null ? "" : row.getDrug_Place_Code().trim());
            
            drug_Use_One_Dosage = row.getDrug_Use_One_Dosage();
            medicaments.setMedicamentsDosage(drug_Use_One_Dosage);
            if (drug_Use_One_Dosage.startsWith("."))
            {
                medicaments.setMedicamentsDosage("0" + drug_Use_One_Dosage);
            }
            
            medicaments.setMedicamentsDosageUnit(row.getDrug_Use_One_Dosage_Unit());
            medicaments.setMedicamentsVolume(row.getDrug_Volume());
            medicaments.setMedicamentsVolumeUnit(row.getDrug_Volume_Unit());
            medicaments.setShelfNo(row.getShelf_No());
            
            String high_risk = row.getHigh_risk();
            try
            {
                medicaments.setMedicamentsDanger(Integer.parseInt(high_risk));
            }
            catch (NumberFormatException e)
            {
                medicaments.setMedicamentsDanger(1);
            }
            medicaments.setMedicamentsSuchama(row.getPinyin_Code());
            
            String price = row.getPrice().trim();
            
            if (price.startsWith("."))
            {
                price = "0" + row.getPrice().trim();
            }
            medicaments.setMedicamentsPrice(price);
            
            try
            {
                medicaments.setMedicamentsTestFlag(Integer.parseInt(row.getSkin_Test_Flag()));
            }
            catch (NumberFormatException e)
            {
                medicaments.setMedicamentsTestFlag(0);
            }
            
            medicaments.setMedicamentsSpecifications(row.getSpecifications());
            
            // 药品分类转换
            if (null != row.getSpecial_Drug() && !"".equals(row.getSpecial_Drug().trim()))
            {
                // 21、抗肿瘤药物
                if ("21".equals(row.getSpecial_Drug()))
                {
                    medicaments.setCategoryId("2002");
                }
                
                // 22、营养药物
                if ("22".equals(row.getSpecial_Drug()))
                {
                    medicaments.setCategoryId("2005");
                }
            }
            
            //  是否溶媒
            medicaments.setMedicamentsMenstruum((StringUtils.isNotEmpty(row.getSolvent_flag()) ? NumberUtils.toInt(row.getSolvent_flag()) :null));
            
            // 是否主药
            medicaments.setMedicamentsIsmaindrug((StringUtils.isNotEmpty(row.getMain_drug_flag()) ? NumberUtils.toInt(row.getMain_drug_flag()) :null));
            
            // 有效期
            medicaments.setEffective_date(row.getEffective_date());
            
            //  药品配置难度系数
            medicaments.setDifficulty_degree(row.getDifficulty_degree());
            
            list.add(medicaments);
        }
        
        medicamentsService.synData(list);
    }
    
    /**
     * 医嘱频次
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgDetailList
     * @see [类、类#方法、类#成员]
     */
    public void analyOrderFrequency(List<OrderFrequencyMsg> msgDetailList)
    {
        OrderFrequencyRow row = null;
        
        MedicalFrequency medicalFrequency = null;
        List<MedicalFrequency> list = new ArrayList<MedicalFrequency>();
        for (OrderFrequencyMsg msg : msgDetailList)
        {
            medicalFrequency = new MedicalFrequency();
            row = msg.getBody().getRow();
            medicalFrequency.setCode(row.getOrder_Frequency_Code());
            medicalFrequency.setName(row.getOrder_Frequency_Name());
            list.add(medicalFrequency);
        }
        
        medicalFrequencyService.synData(list);
    }
    
    /**
     * 用药途径
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgDetailList
     * @see [类、类#方法、类#成员]
     */
    public void analyDrugWayMsg(List<DrugWayMsg> msgDetailList)
    {
        DrugWayRow row = null;
        
        DrugWayBean drugWay = null;
        List<DrugWayBean> list = new ArrayList<DrugWayBean>();
        for (DrugWayMsg msg : msgDetailList)
        {
            drugWay = new DrugWayBean();
            row = msg.getBody().getRow();
            drugWay.setCode(row.getCode());
            drugWay.setName(row.getName());
            drugWay.setId(row.getId());
            list.add(drugWay);
        }

        drugWayService.synData(list);
    }
    
    /**
     * 病区
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgDetailList
     * @see [类、类#方法、类#成员]
     */
    public void analyInPatientAreaMsg(List<InPatientAreaMsg> msgDetailList)
    {
        InPatientAreaRow row = null;
        
        InpatientAreaBean bean = null;
        List<InpatientAreaBean> list = new ArrayList<InpatientAreaBean>();
        for (InPatientAreaMsg msg : msgDetailList)
        {
            bean = new InpatientAreaBean();
            row = msg.getBody().getRow();
            bean.setDeptCode(row.getDept_Code());
            bean.setDeptName(row.getDept_Name());
            list.add(bean);
        }
        
        inpatientAreaService.synData(list);
    }
    
    /**
     * 医嘱数据获取
     * <一句话功能简述>
     * <功能详细描述>
     * @param condition
     * @return
     * @throws IOException 
     * @throws HttpException 
     * @throws ParseException 
     * @see [类、类#方法、类#成员]
     */
    public void getYzData(String condition)
        throws HttpException, IOException
    {
        String synRequest =
            SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.actionAdvice, condition, new Query());
        
        String synRespon = "";
        // 调用接口
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\BS23002.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.actionAdvice, synRequest);
        }
        
        List<String> msgList = SetMessageForSynSZ.analySynYZRespon(synRespon);
        
        if (null != msgList)
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
                analyAdvice(msgDetailList);
            }
        }
    }
    
    /**
     * 医嘱同步
     * <一句话功能简述>
     * <功能详细描述>
     * @throws ParseException 
     * @see [类、类#方法、类#成员]
     */
    public void analyAdvice(List<YzRow> msgDetailList)
    {
        
        SynYzBean synYz = null;
        List<SynYzBean> list = new ArrayList<SynYzBean>();
        for (YzRow row : msgDetailList)
        {
            synYz = exchangeYzBean(row);
            list.add(synYz);
        }
        
        synYzService.synData(list);
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
    public SynYzBean exchangeYzBean(YzRow row)
    {
        SynYzBean synYz = new SynYzBean();
        
        synYz.setAge(row.getAge());
        
        String ageUnit = row.getAge_Unit().trim();
        
        // -1 表示传过来的年龄为中文
        synYz.setAgeUnit(-1);
        
        if (!"".equals(ageUnit))
        {
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
    
    /**
     * 员工信息解析
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgDetailList
     * @see [类、类#方法、类#成员]
     */
    public void analyEmployeeInfoMsg(List<EmployeeInfoMsg> msgDetailList)
    {
        EmployeeInfoRow row = null;
        
        EmployeeInfoBean bean = null;
        List<EmployeeInfoBean> list = new ArrayList<EmployeeInfoBean>();
        for (EmployeeInfoMsg msg : msgDetailList)
        {
            bean = new EmployeeInfoBean();
            row = msg.getBody().getRow();
            bean.setStaff_Code(row.getStaff_Code());
            bean.setStaff_Name(row.getStaff_Name());
            list.add(bean);
        }

        employeeInfoService.synData(list);
    }
    
    /**
     * 药单执行记录数据获取
     * <一句话功能简述>
     * <功能详细描述>
     * @param condition
     * @return
     * @throws IOException 
     * @throws HttpException 
     * @throws ParseException 
     * @see [类、类#方法、类#成员]
     */
    public void getExcuteRecordData(String condition)
        throws HttpException, IOException
    {
        String synRequest =
            SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.actionAdvice, condition, new Query());
        
        String synRespon = "";
        // 调用接口
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\CS23002.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.actionAdvice, synRequest);
        }
        
        List<String> msgList = SetMessageForSynSZ.analySynYZRespon(synRespon);
        
        if (null != msgList)
        {
            JaxbBinder jaxbBinder = new JaxbBinder(YzRecordRow.class);
            List<YzRecordRow> msgDetailList = new ArrayList<YzRecordRow>();
            YzRecordRow msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analyYdRecode(msgDetailList);
            }
        }
    }
    
    /**
     * 药单执行记录
     * <一句话功能简述>
     * <功能详细描述>
     * @throws ParseException 
     * @see [类、类#方法、类#成员]
     */
    public void analyYdRecode(List<YzRecordRow> msgDetailList)
    {
        ExcuteRecordBean bean = null;
        List<ExcuteRecordBean> list = new ArrayList<ExcuteRecordBean>();
        for (YzRecordRow row : msgDetailList)
        {
            bean = exchangeYdRecordBean(row);
            list.add(bean);
        }
        synYdRecordService.synData(list);
    }
    
    public ExcuteRecordBean exchangeYdRecordBean(YzRecordRow row)
    {
        ExcuteRecordBean bean = new ExcuteRecordBean();
        
        bean.setRecipeID((row.getRecipeID() == null) ? "" : row.getRecipeID().trim());
        bean.setGroupNo((row.getGroupNo() == null) ? "" : row.getGroupNo().trim());
        bean.setDrugListID((row.getDrugListID() == null) ? "" : row.getDrugListID().trim());
        bean.setDrugFreq((row.getDrugFreq() == null) ? "" : row.getDrugFreq().trim());
        bean.setDrugCode((row.getDrugCode() == null) ? "" : row.getDrugCode().trim());
        bean.setDrugName((row.getDrugName() == null) ? "" : row.getDrugName().trim());
        
        String drugAmount = (row.getQuantity() == null) ? "" : row.getQuantity().trim();
        bean.setQuantity(drugAmount);
        if (!"".equals(drugAmount) && drugAmount.startsWith("."))
        {
            bean.setQuantity("0" + drugAmount);
        }
        
        bean.setQuantity((row.getQuantity() == null) ? "" : row.getQuantity().trim());
        bean.setQuantityUnit((row.getQuantityUnit() == null) ? "" : row.getQuantityUnit().trim());
        bean.setSchedule((row.getSchedule() == null) ? "" : row.getSchedule().trim());
        bean.setOccDT((row.getOccDT() == null) ? "" : row.getOccDT().trim());
        bean.setChargeDT((row.getChargeDT() == null) ? "" : row.getChargeDT().trim());
        bean.setInfusionDate((row.getInfusionDate() == null) ? "" : row.getInfusionDate().trim());
        bean.setLabelNo((row.getLabelNo() == null) ? "" : row.getLabelNo().trim());
        bean.setBegindt((row.getBegindt() == null) ? "" : row.getBegindt().trim());
        bean.setEnddt((row.getEnddt() == null) ? "" : row.getEnddt().trim());
        
        return bean;
    }
}

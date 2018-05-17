package com.zc.dem.common.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.synmessage.JaxbBinder;
import com.zc.dem.common.synmessage.analyresponse.msg.drug.DrugMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.drugway.DrugWayMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.employeeinfo.EmployeeInfoMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.inpatientarea.InPatientAreaMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.orderfrequency.OrderFrequencyMsg;
import com.zc.dem.common.synmessage.analyresponse.msg.patient.PatientMsg;
import com.zc.dem.common.synmessage.request.ESBEntry.Query;
import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;
import com.zc.dem.syndata.inpatientarea.service.InpatientAreaService;
import com.zc.dem.syndata.yz.service.SynYzService;

/**
 * 某医院同步数据：定时同步使用
 * @author Joehart
 * @version 1.0
 */
@Service("getSynDataForSZ")
public class GetSynDataForSZ
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(GetSynDataForSZ.class);
    
    /**
     * 解析响应数据
     */
    @Resource
    private AnalySynDataForSZ analySynDataForSZ;
    
    /**
     * 病区信息
     */
    @Resource
    private InpatientAreaService inpatientAreaService;
    
    @Resource
    private SynYzService synYzService;
    
    /**
     * 同步病人数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws HttpException
     * @throws IOException
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public boolean synPatient()
        throws HttpException, IOException, ParseException
    {
        // 请求报文
        String synRequest = "";
        
        // 响应报文 
        String synRespon = "";
        
        List<String> msgList = null;
        
        JaxbBinder jaxbBinder = null;
        
        // 设置请求报文
        synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.patient, "", new Query());
        
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\BS23001.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.patient, synRequest);
        }
        
        msgList = SetMessageForSynSZ.analySynRespon(synRespon);
        
        if (null != msgList)
        {
            jaxbBinder = new JaxbBinder(PatientMsg.class);
            List<PatientMsg> msgDetailList = new ArrayList<PatientMsg>();
            PatientMsg msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analySynDataForSZ.analyPatient(msgDetailList);
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * 药品同步
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws IOException 
     * @throws HttpException 
     * @see [类、类#方法、类#成员]
     */
    public boolean synDrug()
        throws HttpException, IOException
    {
        // 请求报文
        String synRequest = "";
        
        // 响应报文 
        String synRespon = "";
        
        List<String> msgList = null;
        
        JaxbBinder jaxbBinder = null;
        
        synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.drug, "", new Query());
        
        // 调用接口        
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\MS23002.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.drug, synRequest);
        }
        
        msgList = SetMessageForSynSZ.analySynRespon(synRespon);
        
        if (null != msgList)
        {
            jaxbBinder = new JaxbBinder(DrugMsg.class);
            List<DrugMsg> msgDetailList = new ArrayList<DrugMsg>();
            DrugMsg msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analySynDataForSZ.analyDrug(msgDetailList);
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * 病区同步
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws HttpException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public boolean synInPatientArea()
        throws HttpException, IOException
    {
        // 请求报文
        String synRequest = "";
        
        // 响应报文 
        String synRespon = "";
        
        List<String> msgList = null;
        
        JaxbBinder jaxbBinder = null;
        
        synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.inPatientArea, "", new Query());
        
        // 调用接口
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\MS23003.txt");
            
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.inPatientArea, synRequest);
        }
        
        msgList = SetMessageForSynSZ.analySynRespon(synRespon);
        
        if (null != msgList)
        {
            jaxbBinder = new JaxbBinder(InPatientAreaMsg.class);
            List<InPatientAreaMsg> msgDetailList = new ArrayList<InPatientAreaMsg>();
            InPatientAreaMsg msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analySynDataForSZ.analyInPatientAreaMsg(msgDetailList);
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean synEmployeeInfo()
        throws HttpException, IOException
    {
        List<String> msgList;
        JaxbBinder jaxbBinder;
        String synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.employeeInfo, "", new Query());
        
        // 调用接口
        String synRespon = "";
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\MS23004.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.employeeInfo, synRequest);
        }
        
        msgList = SetMessageForSynSZ.analySynRespon(synRespon);
        
        if (null != msgList)
        {
            jaxbBinder = new JaxbBinder(EmployeeInfoMsg.class);
            List<EmployeeInfoMsg> msgDetailList = new ArrayList<EmployeeInfoMsg>();
            EmployeeInfoMsg msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analySynDataForSZ.analyEmployeeInfoMsg(msgDetailList);
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * 医嘱频次同步
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws HttpException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public boolean synOederFrequency()
        throws HttpException, IOException
    {
        // 请求报文
        String synRequest = "";
        
        // 响应报文 
        String synRespon = "";
        
        List<String> msgList = null;
        
        JaxbBinder jaxbBinder = null;
        
        synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.orderFrequency, "", new Query());
        
        // 调用接口
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\MS23005.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.orderFrequency, synRequest);
        }
        msgList = SetMessageForSynSZ.analySynRespon(synRespon);
        
        if (null != msgList)
        {
            jaxbBinder = new JaxbBinder(OrderFrequencyMsg.class);
            List<OrderFrequencyMsg> msgDetailList = new ArrayList<OrderFrequencyMsg>();
            OrderFrequencyMsg msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analySynDataForSZ.analyOrderFrequency(msgDetailList);
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * 用药途径同步
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws HttpException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public boolean synDrugWay()
        throws HttpException, IOException
    {
        // 请求报文
        String synRequest = "";
        
        // 响应报文 
        String synRespon = "";
        
        List<String> msgList = null;
        
        JaxbBinder jaxbBinder = null;
        
        synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.drugWay, "", new Query());
        
        // 调用接口
        if ("0".equals(Propertiesconfiguration.getStringProperty("syndata.mode")))
        {
            synRespon =
                FileUtil.readFileByLines(Propertiesconfiguration.getStringProperty("syndata.mode.local.path")
                    + "respon\\MS23006.txt");
        }
        else
        {
            synRespon = SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.drugWay, synRequest);
        }
        msgList = SetMessageForSynSZ.analySynRespon(synRespon);
        
        if (null != msgList)
        {
            jaxbBinder = new JaxbBinder(DrugWayMsg.class);
            List<DrugWayMsg> msgDetailList = new ArrayList<DrugWayMsg>();
            DrugWayMsg msgDetail = null;
            for (String msg : msgList)
            {
                msgDetail = jaxbBinder.fromXml(msg.replaceAll("&", "&amp;"));
                
                msgDetailList.add(msgDetail);
            }
            
            if (null != msgDetailList)
            {
                analySynDataForSZ.analyDrugWayMsg(msgDetailList);
            }
            
            return true;
        }
        return false;
    }
    
    /**
     * 医嘱同步
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean synAdvice()
    {
        // 获取需要查询的病区编码
        List<InpatientAreaBean> inPatientAreaList = inpatientAreaService.queryInPatientAreaList();
        
        // 拼接医嘱查询条件
        String condition = "";
        if (null != inPatientAreaList)
        {
            // 检索dem与pivas状态不一致
            synYzService.checkYzStatus();
            
            try
            {
                // 根据病区新启线程获取数据，避免大数据
                for (InpatientAreaBean bean : inPatientAreaList)
                {
                    condition =
                        "Order_Open_Dept_Code = "
                            + bean.getDeptCode()
                            + " AND Dose_Way_Code IN (2,3,99,220,221,222) "
                            + "AND ORDER_CATEG_CODE in ('0','1') "
                            + "AND Order_Execute_Status in ('0','1','2') "
                            + "AND CONFIRM_DATE IS NOT NULL "
                            + "AND (Order_Stop_Date IS null OR Order_Stop_Date >= to_date(to_char(sysdate, 'yyyy-mm-dd') ,'yyyy-mm-dd'))";
                    
                    analySynDataForSZ.getYzData(condition);
                }
            }
            catch (HttpException e)
            {
                return false;
            }
            catch (IOException e)
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 药单执行记录同步
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean synExcuteRecord()
    {
        try
        {
            analySynDataForSZ.getExcuteRecordData("");
        }
        catch (HttpException e)
        {
            log.error(e.getMessage(), e);
            return false;
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            return false;
        }
        
        return true;
    }
}

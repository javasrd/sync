package com.zc.dem.restful;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.zc.dem.common.util.AnalySynDataForSZ;
import com.zc.dem.common.util.EncodeUtil;
import com.zc.dem.common.util.FileUtil;
import com.zc.dem.common.util.Propertiesconfiguration;
import com.zc.dem.common.util.SetMessageForSynSZ;
import com.zc.dem.common.util.SetPushToMQMessage;

/**
 * 某医院同步数据
 * @author Joehart
 * @version 1.0
 */
@Service("synDataForSZ")
public class SynDataForSZ
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(SynDataForSZ.class);
    
    /**
     * 加密密钥
     */
    private static final String AES128_KEY = Propertiesconfiguration.getStringProperty("jdbc.encrypt.key");
    
    /**
     * 编码格式
     */
    private static final String AES128_ENCODING = Propertiesconfiguration.getStringProperty("jdbc.encrypt.encoding");
    
    /**
     * 对接失败
     */
    private final static String SYN_FAIL = "201";
    
    /**
     * 解析响应数据
     */
    private AnalySynDataForSZ analySynDataForSZ;
    
    /**
     * 推送消息到mq
     */
    private SetPushToMQMessage setPushToMQMessage;
    
    /**
     * 某医院同步数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param resultMap
     * @param dataType
     * @return
     * @throws IOException 
     * @throws HttpException 
     * @throws JSONException
     * @throws ParseException 
     * @throws UnsupportedEncodingException
     * @see [类、类#方法、类#成员]
     */
    public String synData(JSONObject data, Map<String, String> resultMap, Integer dataType)
        throws HttpException, IOException, JSONException, ParseException
    {
        // 请求报文
        String synRequest = "";
        
        // 响应报文 
        String synRespon = "";
        
        List<String> msgList = null;
        
        JaxbBinder jaxbBinder = null;
        switch (dataType)
        {
            case DemConstant.synDataType.ACTION_PATIENT:
                
                // 设置请求报文
                synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.patient, "", new Query());
                
                // 调用接口
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
                
                if (null == msgList)
                {
                    resultMap.put("result", SYN_FAIL);
                }
                else
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
                }
                
                break;
            
            case DemConstant.synDataType.ACTION_ADVICE:
                
                // 获取需要查询的病区编码
                String[] deptCodes = data.getString("deptCode").split(",");
                
                // 拼接医嘱查询条件
                String condition = "";
                // 根据病区，避免大数据
                for (String deptCode : deptCodes)
                {
                    condition =
                        "Order_Open_Dept_Code = "
                            + deptCode
                            + " AND Dose_Way_Code IN (2,3,99,220,221,222) "
                            + "AND ORDER_CATEG_CODE in ('0','1') "
                            + "AND Order_Execute_Status in ('0','1','2') "
                            + "AND CONFIRM_DATE IS NOT NULL "
                            + "AND (Order_Stop_Date IS null OR Order_Stop_Date >= to_date(to_char(sysdate , 'yyyy-mm-dd') ,'yyyy-mm-dd'))";
                    analySynDataForSZ.getYzData(condition);
                }
                
                break;
            
            case DemConstant.synDataType.ACTION_DRUG:
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
                
                if (null == msgList)
                {
                    resultMap.put("result", SYN_FAIL);
                }
                else
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
                }
                
                break;
            
            case DemConstant.synDataType.ACTION_INPATIENT_AREA:
                
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
                    synRespon =
                        SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.inPatientArea, synRequest);
                }
                msgList = SetMessageForSynSZ.analySynRespon(synRespon);
                
                if (null == msgList)
                {
                    resultMap.put("result", SYN_FAIL);
                }
                else
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
                }
                
                break;
            
            case DemConstant.synDataType.ACTION_ORDER_FREQUENCY:
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
                    synRespon =
                        SetMessageForSynSZ.sendRequestMessage(DemConstant.synSZDataCode.orderFrequency, synRequest);
                }
                msgList = SetMessageForSynSZ.analySynRespon(synRespon);
                
                if (null == msgList)
                {
                    resultMap.put("result", SYN_FAIL);
                }
                else
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
                }
                break;
            
            case DemConstant.synDataType.ACTION_DRUGWAY:
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
                
                if (null == msgList)
                {
                    resultMap.put("result", SYN_FAIL);
                }
                else
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
                }
                
                break;
                
            case DemConstant.synDataType.ACTION_EMPLOYEE:
                // 获取员工
                synEmployeeInfo();
                
                break;
            
            default:
                break;
        }
        return exchangeResult(resultMap);
    }
    
    private void synEmployeeInfo()
        throws HttpException, IOException
    {
        String synRequest;
        String synRespon;
        List<String> msgList;
        JaxbBinder jaxbBinder;
        synRequest = SetMessageForSynSZ.setSynReq(DemConstant.synSZDataCode.employeeInfo, "", new Query());
        
        // 调用接口
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
        }
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
        
        if (SYN_FAIL.equals(resultMap.get("result").toString()))
        {
            return result.toString();
        }
        
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
                
                default:
                    param = setPushToMQMessage.setPatient();
                    break;
            }
            
            result.put("param", EncodeUtil.encode(EncodeUtil.GZip(param.toString()).getBytes("iso8859-1")));
        }
        return result.toString();
    }
    
    @Autowired
    public void setAnalySynDataForSZ(AnalySynDataForSZ analySynDataForSZ)
    {
        this.analySynDataForSZ = analySynDataForSZ;
    }
    
    @Autowired
    public void setSetPushToMQMessage(SetPushToMQMessage setPushToMQMessage)
    {
        this.setPushToMQMessage = setPushToMQMessage;
    }
    
}

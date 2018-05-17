package com.zc.dem.restful;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.dem.common.RestfulRetCodeConfiguration;
import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.util.InvokeSystemCmd;
import com.zc.dem.common.util.Propertiesconfiguration;
import com.zc.dem.common.util.SetPushToMQMessage;

/**
 * ETL脚本同步数据
 * @author Joehart
 * @version 1.0
 */
@Service("synDataByETL")
public class SynDataByETL
{
    /**
     * 对接失败
     */
    private final static String SYN_FAIL = "201";
    
    /**
     * 加密密钥
     */
    private static final String AES128_KEY = Propertiesconfiguration.getStringProperty("jdbc.encrypt.key");
    
    /**
     * 编码格式
     */
    private static final String AES128_ENCODING = Propertiesconfiguration.getStringProperty("jdbc.encrypt.encoding");
    
    /**
     * 推送消息到mq
     */
    private SetPushToMQMessage setPushToMQMessage;
    
    /**
     * ETL脚本同步数据模式
     * <一句话功能简述>
     * <功能详细描述>
     * @param resultMap
     * @param dataType
     * @return
     * @throws JSONException
     * @throws UnsupportedEncodingException
     * @see [类、类#方法、类#成员]
     */
    public String synData(Map<String, String> resultMap, Integer dataType)
        throws JSONException, UnsupportedEncodingException
    {
        //获取对应任务的脚本文件路径
        String shellPath = "";
        switch (dataType)
        {
            case DemConstant.synDataType.ACTION_PATIENT:
                shellPath = RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.patient);
                break;
            
            case DemConstant.synDataType.ACTION_ADVICE:
                shellPath = RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.drugway);
                break;
            
            case DemConstant.synDataType.ACTION_DRUG:
                shellPath = RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.medicamentDict);
                break;
            
            case DemConstant.synDataType.ACTION_INPATIENT_AREA:
                shellPath = RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.inpatientArea);
                break;
            
            case DemConstant.synDataType.ACTION_ORDER_FREQUENCY:
                shellPath = RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.drugfrequency);
                break;
            
            case DemConstant.synDataType.ACTION_DRUGWAY:
                shellPath =
                    RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.drugAdministration);
                break;
            
            default:
                shellPath = RestfulRetCodeConfiguration.getStringProperty(DemConstant.actionTypeDesc.patient);
                break;
        }
        resultMap.put("shellPath", shellPath);
        return excuteShell(resultMap);
    }
    
    private String excuteShell(Map<String, String> resultMap)
        throws JSONException, UnsupportedEncodingException
    {
        String shellPath = resultMap.get("shellPath");
        
        String respon = "";
        // 调ETL脚本
        if (shellPath != null && shellPath.length() > 0)
        {
            // 执行结果失败
            if (InvokeSystemCmd.exeSysCommand(shellPath) != DemConstant.operateResult.suc)
            {
                resultMap.put("result", SYN_FAIL);
                respon = exchangeResult(resultMap, false);
            }
            else
            {
                // 拼接响应数据
                respon = exchangeResult(resultMap, true);
            }
        }
        
        return respon;
    }
    
    /**
     * 封装响应消息
     * 
     * @param map
     * @return
     * @throws JSONException
     * @throws UnsupportedEncodingException 
     * @see [类、类#方法、类#成员]
     */
    private String exchangeResult(Map<String, String> resultMap, boolean isSuccess)
        throws JSONException, UnsupportedEncodingException
    {
        JSONObject result = new JSONObject();
        
        JSONObject param = new JSONObject();
        
        result.put("result", resultMap.get("result"));
        
        if (!isSuccess)
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
            
            result.put("param", param.toString());
            
        }
        
        return result.toString();
    }
    
    @Autowired
    public void setSetPushToMQMessage(SetPushToMQMessage setPushToMQMessage)
    {
        this.setPushToMQMessage = setPushToMQMessage;
    }
}

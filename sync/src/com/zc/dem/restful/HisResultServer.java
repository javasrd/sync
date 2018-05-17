package com.zc.dem.restful;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpException;
import org.apache.shiro.crypto.CryptoException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zc.dem.common.util.AnalySynDataForSZ;
import com.zc.dem.common.util.Propertiesconfiguration;
import com.zc.dem.syndata.excuteRecord.bean.ExcuteRecordBean;
import com.zc.dem.syndata.excuteRecord.service.SynYdRecordService;
import com.zc.dem.syndata.yz.service.SynYzService;
import com.zc.synhis.service.SynYdRecordByHisService;

/**
 * HIS数据读取接口
 * @author Joehart
 * @version 1.0
 */
@Component
@Path("restful")
public class HisResultServer
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(HisResultServer.class);
    
    /**
     * 加密密钥
     */
    //private static final String AES128_KEY = Propertiesconfiguration.getStringProperty("jdbc.encrypt.key");
    
    /**
     * 编码格式
     */
    //private static final String AES128_ENCODING = Propertiesconfiguration.getStringProperty("jdbc.encrypt.encoding");
    
    /**
     * 同步模式：0ETL
     */
    private static final String SYNCHRONIZATION_MODE_ETL = "0";
    
    /**
     * 同步模式：0ETL 1HIS 2VIEW
     */
    private static final String SYNCHRONIZATION_MODE_VIEW = "2";
    
    /**
     * 对接成功
     */
    private final static String SYN_SUCCESS = "200";
    
    /**
     * 对接失败
     */
    private final static String SYN_FAIL = "201";
    
    @Resource
    private SynDataForSZ synDataForSZ;
    
    @Resource
    private SynDataByETL synDataByETL;
    
    @Resource
    private SynDataByView synDataByView;
    
    @Resource
    private AnalySynDataForSZ analySynDataForSZ;
    
    /**
     * 医嘱
     */
    @Resource
    private SynYzService synYzService;
    
    @Resource
    private SynYdRecordByHisService synYdRecordByHisService;
    
    @Resource
    private SynYdRecordService synYdRecordService;
    
    /**
     * HIS数据读取接口
     * @param strParams 请求信息
     * @return 响应
     * @throws JSONException
     * @throws NumberFormatException
     * @throws ParseException
     * @throws CryptoException 
     * @throws IOException 
     * @throws HttpException 
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("his/data")
    public String getHisData(String strParams)
        throws JSONException, NumberFormatException, ParseException, CryptoException, HttpException, IOException
    {
        log.info("the getHisData request: " + strParams);
        
        // 同步模式
        String synchronizationMode = Propertiesconfiguration.getStringProperty("synhis.synchronizationmode");
        
        Map<String, String> resultMap = new HashMap<String, String>();
        
        resultMap.put("result", SYN_SUCCESS);
        
        // 解析请求消息
        String param = new JSONObject(strParams).getString("param");
        JSONObject data = new JSONObject(param);
        
        Integer dataType = null;
        if (null != data)
        {
            try
            {
                dataType = data.getInt("dateType");
                resultMap.put("dataType", String.valueOf(dataType));
                // 根据同步数据类型选择执行脚本路径
                if (null != dataType)
                {
                    // 判断当前同步模式
                    if (SYNCHRONIZATION_MODE_ETL.equals(synchronizationMode))
                    {
                        return synDataByETL.synData(resultMap, dataType);
                    }
                    else if (SYNCHRONIZATION_MODE_VIEW.equals(synchronizationMode))
                    {
                        return synDataByView.synData(data,resultMap, dataType);
                    }
                    else
                    {
                        return synDataForSZ.synData(data, resultMap, dataType);
                    }
                    
                }
            }
            catch(Exception e)
            {
                log.error(e.getMessage(),e);
                JSONObject result = new JSONObject();
                result.put("result", SYN_FAIL);
                return result.toString();
            }
            
        }
        JSONObject result = new JSONObject();
        result.put("result", SYN_FAIL);
        return result.toString();
    }
    
    /**
     * 检查医嘱状态读取接口
     * 
     * @param strParams
     * @return
     * @throws JSONException
     * @throws NumberFormatException
     * @throws ParseException
     * @throws CryptoException 
     * @throws IOException 
     * @throws HttpException 
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("doctor/data")
    public String checkOrderStatus(String strParams) throws Exception
    {
        // 解析请求消息
        String paramData = new JSONObject(strParams).getString("param");
        JSONObject condition = null;
        if (null != paramData && !"".equals(paramData))
        {
            JSONObject data = new JSONObject(paramData);
            if (null != data && data.length() > 0)
            {
                condition = new JSONObject(data.getString("condition"));
            }
        }
        
        JSONObject result = new JSONObject();
        result.put("result", SYN_SUCCESS);
        
        JSONObject param = new JSONObject();
        param.put("result", "0");
        if (null != condition)
        {
            Map<String, Object>map = new HashMap<String, Object>();
            
            map.put("order_group_no", condition.getString("order_group_no"));   
            map.put("occdt", condition.getString("occdt")); 
            
            try
            {
                List<ExcuteRecordBean> recordList = synYdRecordService.qryYdStateList(map);
                
                if(CollectionUtils.isNotEmpty(recordList))
                {
                    param.put("result", recordList.get(0).getState());
                }
            }
            catch (Exception e)
            {
                log.error("检查药单状态，同步失败！", e.getMessage());
                result.put("result", SYN_FAIL);
            }
            
        }
        
        result.put("param", param.toString());
        log.debug(result.toString());
        return result.toString();
    }
    
    
    /**
     * 配置费收费接口
     * 
     * @param strParams
     * @return
     * @throws JSONException
     * @throws NumberFormatException
     * @throws ParseException
     * @throws UnsupportedEncodingException 
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("fee/data")
    public String setFymxData(String strParams)
        throws JSONException, NumberFormatException, ParseException, UnsupportedEncodingException,
        ClassNotFoundException, SQLException
    {
        // 解析请求消息
        JSONObject request = new JSONObject(strParams);
        
        JSONObject result = new JSONObject();
        result.put("result", SYN_SUCCESS);
        JSONObject param = new JSONObject();
        
        String paramData = request.getString("param");
        
        JSONObject data = null;
        if (null != paramData && !"".equals(paramData))
        {
            data = new JSONObject(paramData);
        }
        
        // 暂时先只把接口数据获取到，因为不确定医院接口对接方式，先直接返回成功。
        if (!"1".equals(Propertiesconfiguration.getStringProperty("setfymxdata.mode")))
        {
            //返回成功失败标志 1  成功  -1 失败
            param.put("alret", 1);
            
            // 失败时返回错误信息
            param.put("aserrtext", "");
        }
        else
        {
            // 连接服务器数据库
            Connection conn = connectOracle();
            CallableStatement proc = null;
            try
            {
                proc = conn.prepareCall("{ call  lchis.prc_pivas_charge(?, ?, ?, ?) }");
                
                String caseID = "0";
                if(data.has("alzyh"))
                {
                    caseID= data.getString("alzyh").substring(4);
                }
                // 住院流水号
                proc.setString(1, caseID);
                
                // 项目编码
                proc.setString(2, (data.has("alfyxh") ? data.getString("alfyxh") : "0"));
                //proc.setString(3, (data.has("adfysl") ? data.setString("adfysl") : "0"));
                
                // 操作员编码
                String oprCode = "";
                if(data.has("aszxgh") )
                {
                    oprCode = data.getString("aszxgh");
                    
                    if(oprCode.length() == 4)
                    {
                        oprCode = "00" + oprCode;
                    }
                    
                    if(oprCode.length() == 5)
                    {
                        oprCode = "0" + oprCode;
                    }
                }
                proc.setString(3,oprCode);
//                proc.registerOutParameter(4, Types.INTEGER);
                proc.registerOutParameter(4, Types.VARCHAR);
                proc.execute();
                
                String resultCode = proc.getString(4);
                
                if("1".equals(resultCode))
                {
                    //返回成功失败标志 1  成功  -1 失败
                    param.put("alret", 1);
                    param.put("aserrtext", "配置费收费成功");
                }
                else
                {
                    //返回成功失败标志 1  成功  -1 失败
                    param.put("alret", -1);
                    
                    // 失败时返回错误信息
                    param.put("aserrtext", "配置费收费失败");
                }
                
            }
            catch (SQLException e)
            {
                result.put("result", SYN_FAIL);
            }
            
            finally
            {
                
                try
                {
                    proc.close();
                }
                catch (SQLException e)
                {
                    log.error(e.getMessage(), e);
                }
                
                conn.close();
                
            }
        }
        
        result.put("param", param.toString());
        return result.toString();
    }
    
    /**
     * 连接oracle数据库
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    private Connection connectOracle()
        throws ClassNotFoundException, SQLException
    {
        Connection conn = null;
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        String url = Propertiesconfiguration.getStringProperty("setfymxdata.address");
        
        String UserName = Propertiesconfiguration.getStringProperty("setfymxdata.username");
        
        String Password = Propertiesconfiguration.getStringProperty("setfymxdata.password");
        
        conn = DriverManager.getConnection(url, UserName, Password);
        return conn;
    }
    
    @Autowired
    public void setSynDataForSZ(SynDataForSZ synDataForSZ)
    {
        this.synDataForSZ = synDataForSZ;
    }
    
    @Autowired
    public void setSynDataByETL(SynDataByETL synDataByETL)
    {
        this.synDataByETL = synDataByETL;
    }
    
}

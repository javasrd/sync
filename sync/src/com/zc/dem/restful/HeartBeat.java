package com.zc.dem.restful;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpException;
import org.apache.shiro.crypto.CryptoException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HIS数据读取接口
 * @author Joehart
 * @version 1.0
 */
@Component
@Path("heart")
public class HeartBeat
{

    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(HeartBeat.class);
    
    /**
     * 心跳接口
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
    @Path("heartbeat")
    public String heartBeat(String strParams)
        throws JSONException, NumberFormatException, ParseException, CryptoException, HttpException, IOException
    {
        // 解析请求消息
        int ret = -1;
        JSONObject object = new JSONObject(strParams);
        try
        {
            String ip = object.getString("ip");
            if(ip != null)
            {
                if(!ip.isEmpty())
                {
                    log.info("Receive heartbeat request:" + ip);
                    ret = 0;
                }
            }
        }
        catch(org.codehaus.jettison.json.JSONException e)
        {
            log.error("Parse json failed:" + e.getMessage());
        }
        
        JSONObject result = new JSONObject();
        result.put("ret",ret);
        return result.toString();
    }
    
}

package com.zc.dem.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang3.StringEscapeUtils;

import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.synmessage.JaxbBinder;
import com.zc.dem.common.synmessage.analyresponse.SoapEnvelope;
import com.zc.dem.common.synmessage.request.HIPMessageServer;
import com.zc.dem.common.synmessage.request.SoapBody;
import com.zc.dem.common.synmessage.request.SoapEnvelopeReq;
import com.zc.dem.common.synmessage.request.ESBEntry.AccessControl;
import com.zc.dem.common.synmessage.request.ESBEntry.ESBEntry;
import com.zc.dem.common.synmessage.request.ESBEntry.MessageESBEntryHeader;
import com.zc.dem.common.synmessage.request.ESBEntry.MsgESBEntryInfo;
import com.zc.dem.common.synmessage.request.ESBEntry.Query;

/**
 * 某医院数据同步请求消息拼装
 * @author Joehart
 * @version 1.0
 */
public class SetMessageForSynSZ
{
    /**
     * 排头
     */
    private static final String SCRAP = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>";
    
    /**
     * 同步成功代码
     */
    private static final String SYN_SUCCESS = "1";
    
    /**
     * 拼接请求
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String setSynReq(String Fid, String msg, Query query)
    {
        JaxbBinder jaxbBinder = new JaxbBinder(ESBEntry.class);
        ESBEntry esbEntry = new ESBEntry();
        MessageESBEntryHeader messageHeader = new MessageESBEntryHeader();
        AccessControl accessControl = new AccessControl();
        accessControl.setFid(Fid);
        messageHeader.setFid(Fid);
        MsgESBEntryInfo msgInfo = new MsgESBEntryInfo();
        msgInfo.setMsg(msg);
        msgInfo.setQuery(query);
        
        esbEntry.setAccessControl(accessControl);
        esbEntry.setMessageHeader(messageHeader);
        esbEntry.setMsgInfo(msgInfo);
        
        String esbentry = jaxbBinder.toXml(esbEntry, "utf-8");
        
        jaxbBinder = new JaxbBinder(SoapEnvelopeReq.class);
        SoapEnvelopeReq req = new SoapEnvelopeReq();
        SoapBody body = new SoapBody();
        HIPMessageServer hipMessageServer = new HIPMessageServer();
        hipMessageServer.setInPut(esbentry.replace(SCRAP, ""));
        body.setHipMessageServer(hipMessageServer);
        req.setBody(body);
        
        return jaxbBinder.toXml(req, "utf-8")
            .replace("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>", "")
            .replaceAll("\n", "");
    }
    
    /**
     * 解析响应报文
     * <一句话功能简述>
     * <功能详细描述>
     * @param synRespon
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<String> analySynRespon(String synRespon)
    {
        JaxbBinder jaxbBinder = new JaxbBinder(SoapEnvelope.class);
        SoapEnvelope envelopecc = jaxbBinder.fromXml(synRespon.replaceAll("&", "&amp;"));
        
        if (null != envelopecc && null != envelopecc.getBody()
            && null != envelopecc.getBody().getHipMessageServerResponse()
            && null != envelopecc.getBody().getHipMessageServerResponse().getOutPut()
            && null != envelopecc.getBody().getHipMessageServerResponse().getOutPut().getEsbEntry())
        {
            String retCode =
                envelopecc.getBody().getHipMessageServerResponse().getOutPut().getEsbEntry().getRetInfo().getRetCode();
            
            if (SYN_SUCCESS.equals(retCode))
            {
                List<String> msgList =
                    envelopecc.getBody()
                        .getHipMessageServerResponse()
                        .getOutPut()
                        .getEsbEntry()
                        .getMsgInfo()
                        .getMsgList();
                return msgList;
            }
        }
        
        return null;
    }
    
    /**
     * 解析响应报文
     * <一句话功能简述>
     * <功能详细描述>
     * @param synRespon
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<String> analySynYZRespon(String synRespon)
    {
        JaxbBinder jaxbBinder = new JaxbBinder(SoapEnvelope.class);
        SoapEnvelope envelopecc = jaxbBinder.fromXml(synRespon.replaceAll("&", "&amp;"));
        
        if (null != envelopecc && null != envelopecc.getBody()
            && null != envelopecc.getBody().getHipMessageServerResponse()
            && null != envelopecc.getBody().getHipMessageServerResponse().getOutPutYz()
            && null != envelopecc.getBody().getHipMessageServerResponse().getOutPutYz().getEsbEntry())
        {
            String retCode =
                envelopecc.getBody()
                    .getHipMessageServerResponse()
                    .getOutPutYz()
                    .getEsbEntry()
                    .getRetInfo()
                    .getRetCode();
            
            if (SYN_SUCCESS.equals(retCode))
            {
                List<String> msgList =
                    envelopecc.getBody()
                        .getHipMessageServerResponse()
                        .getOutPutYz()
                        .getEsbEntry()
                        .getMsgInfo()
                        .getMsgList();
                return msgList;
            }
        }
        
        return null;
    }
    
    /**
     * 发送请求报文
     * <一句话功能简述>
     * <功能详细描述>
     * @param url
     * @param request
     * @return
     * @throws IOException 
     * @throws HttpException 
     * @see [类、类#方法、类#成员]
     */
    public static String sendRequestMessage(String fid, String request)
        throws HttpException, IOException
    {
        // 获取同步地址
        String synUrl = getSendUrlByFid(fid);
        
        PostMethod postMethod = new PostMethod(synUrl);
        byte[] b = request.getBytes("utf-8");
        InputStream inputStream = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(inputStream, b.length, "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(re);
        
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
        String respon = postMethod.getResponseBodyAsString();
        return StringEscapeUtils.unescapeXml(respon);
    }
    
    /**
     * 根据数据类型获取url
     * <一句话功能简述>
     * <功能详细描述>
     * @param fid
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getSendUrlByFid(String fid)
    {
        String synUrl = Propertiesconfiguration.getStringProperty("synhis.synchronizationurl").replaceAll("FID", fid);
        
        if (DemConstant.synSZDataCode.orderFrequency.equals(fid) || DemConstant.synSZDataCode.drugWay.equals(fid))
        {
            synUrl =
                synUrl.replace("PORT",
                    Propertiesconfiguration.getStringProperty("synhis.synchronization.port.orderFrequency"));
        }
        else if (DemConstant.synSZDataCode.actionAdvice.equals(fid))
        {
            synUrl =
                synUrl.replace("PORT",
                    Propertiesconfiguration.getStringProperty("synhis.synchronization.port.actionAdvice"));
        }
        else if (DemConstant.synSZDataCode.drug.equals(fid))
        {
            synUrl =
                synUrl.replace("PORT",
                    Propertiesconfiguration.getStringProperty("synhis.synchronization.port.drug"));
        }
        else
        {
            synUrl =
                synUrl.replace("PORT", Propertiesconfiguration.getStringProperty("synhis.synchronization.port.deaful"));
        }
        return synUrl;
    }
}

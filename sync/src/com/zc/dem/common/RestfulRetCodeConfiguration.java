package com.zc.dem.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性文件管理类 提供读取系统配置文件的属性值
 * @author Joehart
 * @version 1.0
 */
public class RestfulRetCodeConfiguration
{
    /**
     * 加载错误码属性配置文件
     */
    private static final String RESTFUL_PROPERTIES_FILE = "/restfulcode.properties";
    
    /**
     * 加载任务执行脚本配置文件
     */
    private static final String TASK_FILE_PATH = "/taskfilepath.properties";
    
    /**
     * 加载日志文件
     */
    private static final Log logger =
        LogFactory.getLog(RestfulRetCodeConfiguration.class);
    
    /**
     * 定义Properties对象
     */
    private static Properties p = null;
    
    static
    {
        init();
    }
    
    /**
     * <默认构造函数>
     */
    public RestfulRetCodeConfiguration()
    {
    }
    
    /**
     * 初始化加载系统配置文件 
     * 
     * @see [类、类#方法、类#成员]
     */
    protected static void init()
    {
        InputStream in1 = null;
        InputStream in2 = null;
        
        try
        {
            // 读取系统配置文件文件流
            in1 = RestfulRetCodeConfiguration.class.getResourceAsStream(RESTFUL_PROPERTIES_FILE);
            in2 = RestfulRetCodeConfiguration.class.getResourceAsStream(TASK_FILE_PATH);

            if (in1 != null)
            {
                if (p == null)
                {
                    p = new Properties();
                }
                p.load(in1);
            }
            
            if (in2 != null)
            {
                if (p == null)
                {
                    p = new Properties();
                }
                p.load(in2);
            }
        }
        catch (IOException e)
        {
            logger.error("load " + RESTFUL_PROPERTIES_FILE
                + " into Constants error!");
        }
        finally
        {
            if (in1 != null)
            {
                try
                {
                    in1.close();
                }
                catch (IOException e)
                {
                    logger.error("close " + RESTFUL_PROPERTIES_FILE + " error!");
                }
            }
            
            if (in2 != null)
            {
                try
                {
                    in2.close();
                }
                catch (IOException e)
                {
                    logger.error("close " + TASK_FILE_PATH + " error!");
                }
            }
        }
    }
    
    /**
     * 获取系统值  
     * 
     * @param key 传入的key
     * @param defaultValue 默认值
     * @return 相应的Value 
     * @see [类、类#方法、类#成员]
     */
    protected static String getProperty(String key, String defaultValue)
    {
        return p.getProperty(key, defaultValue);
    }
    
    /**
     * 根据传入的key值，获取相应的Value 
     * 
     * @param key  传入的key
     * @return 相应的Value
     * @see [类、类#方法、类#成员]
     */
    public static String getStringProperty(String key)
    {
        if (null != p)
        {
            return p.getProperty(key);
        }
        return "";
    }
}

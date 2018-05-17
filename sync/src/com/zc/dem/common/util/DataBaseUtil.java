package com.zc.dem.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 数据库操作工具类
 * @author Joehart
 * @version 1.0
 */
@Service("dataBaseUtil")
public class DataBaseUtil
{
    private static final Logger log = LoggerFactory.getLogger(DataBaseUtil.class);
    
    /**
     * 连接oracle数据库
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public Connection connectOracle()
        throws SQLException, ClassNotFoundException
    {
        Connection conn = null;
        
        Class.forName(Propertiesconfiguration.getStringProperty("synhis.data.driver"));
        
        String url = Propertiesconfiguration.getStringProperty("synhis.data.address");
        
        String UserName = Propertiesconfiguration.getStringProperty("synhis.data.username");
        
        String Password = Propertiesconfiguration.getStringProperty("synhis.data.password");
        
        conn = DriverManager.getConnection(url, UserName, Password);
        
        return conn;
    }
    
    public ResultSet qryResult(String sql)
    {
        // 创建一个数据库连接
        Connection conn = null;
        
        // 创建预编译语句对象，一般都是用这个而不用Statement
        PreparedStatement pre = null;
        
        ResultSet result = null;
        try
        {
            conn = connectOracle();
            
            // 实例化预编译语句
            pre = conn.prepareStatement(sql);
            
            // 执行查询，注意括号中不需要再加参数
            result = pre.executeQuery();
            
        }
        catch (ClassNotFoundException e)
        {
            log.error("连接HIS数据库失败！", e.getMessage());
        }
        catch (SQLException e)
        {
            log.error("连接HIS数据库失败！", e.getMessage());
        }
        
        finally
        {
            
            try
            {
                conn.close();
                pre.close();
            }
            catch (SQLException e)
            {
                log.error("关闭连接HIS数据库失败！", e.getMessage());
            } 
        }
        
        return result;
        
    }
}

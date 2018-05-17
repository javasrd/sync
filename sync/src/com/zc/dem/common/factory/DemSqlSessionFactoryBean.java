package com.zc.dem.common.factory;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;

/**
 * 由于org.mybatis.spring.SqlSessionFactoryBean在解析mybatis配置文件时，
 * 不会将出错的的内容输入出来，导致很难定位mybatis配置文件的错误，这里会将Exception打印出来
 * @author Joehart
 * @version 1.0
 */
public class DemSqlSessionFactoryBean extends SqlSessionFactoryBean
{
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    protected SqlSessionFactory buildSqlSessionFactory()
        throws IOException
    {
        try
        {
            return super.buildSqlSessionFactory();
        }
        catch (NestedIOException e)
        {
            //解析错误时输出Exception
            logger.error("Nested Exception: \r\n" + e.getMessage());
            throw e;
        }
    }
    
}

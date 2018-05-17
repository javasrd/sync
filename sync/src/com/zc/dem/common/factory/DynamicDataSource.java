package com.zc.dem.common.factory;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 数据源配置
 * @author Joehart
 * @version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceContextHolder.getDBType();  
    }  
}

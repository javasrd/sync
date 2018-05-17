package com.zc.dem.backup.dao;

import org.apache.ibatis.annotations.Param;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.backup.bean.ServerNodeBean;

/**
 * 检查结点类型是主还是备
 *
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("BackDAO")
public interface BackDAO
{
    /**
     * 获取当前服务器结点
     * @param bean 查询参数
     * @return 页码
     * @see [类、类#方法、类#成员]
     */
    ServerNodeBean getServerNode(@Param("ip") String ip,@Param("pivasuser") String pivasuser);
}

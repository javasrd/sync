package com.zc.synhis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.yz.bean.SynYzBean;

/**
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("synYzByHisDAO")
public interface SynYzByHisDAO
{
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SynYzBean> qryYzList(@Param("map") Map<String, Object> map);
}

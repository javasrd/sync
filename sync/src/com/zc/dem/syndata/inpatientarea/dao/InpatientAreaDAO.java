package com.zc.dem.syndata.inpatientarea.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;

/**
 * 病区数据DAO
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("inpatientAreaDAO")
public interface InpatientAreaDAO extends BaseMapper<InpatientAreaBean, Long>
{
    /**
     * 查询所有病区
     * <br> 
     * @param condition
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InpatientAreaBean> queryInPatientAreaList();
}

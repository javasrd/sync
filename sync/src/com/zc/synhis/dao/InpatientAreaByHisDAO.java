package com.zc.synhis.dao;

import java.util.List;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.syndata.inpatientarea.bean.InpatientAreaBean;

/**
 * 病区数据DAO
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("inpatientAreaByHisDAO")
public interface InpatientAreaByHisDAO
{
    /**
     * 通过调用his病区视图，获取数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InpatientAreaBean> qryInpatientAreaList();
}

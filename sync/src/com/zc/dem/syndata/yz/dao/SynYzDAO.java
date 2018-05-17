package com.zc.dem.syndata.yz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.yz.bean.SynYzBean;

/**
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("synYzDAO")
public interface SynYzDAO extends BaseMapper<SynYzBean, Long>
{
    List<SynYzBean> queryYZForHisDel();
    
    void updateYzByCondition(SynYzBean bean);
    
    void checkYzStatus();
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SynYzBean> qryYzList(@Param("map") Map<String, Object> map);
}

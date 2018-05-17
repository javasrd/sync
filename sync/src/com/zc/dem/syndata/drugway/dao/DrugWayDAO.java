package com.zc.dem.syndata.drugway.dao;

import com.zc.base.orm.mybatis.annotation.MyBatisRepository;
import com.zc.dem.common.dao.BaseMapper;
import com.zc.dem.syndata.drugway.bean.DrugWayBean;

/**
 * 用药途径表PIVAS_DRUGWAY对应DAO
 * @author Joehart
 * @version 1.0
 */
@MyBatisRepository("drugWayDAO")
public interface DrugWayDAO extends BaseMapper<DrugWayBean, Long>
{
}
